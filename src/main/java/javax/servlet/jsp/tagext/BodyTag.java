/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package javax.servlet.jsp.tagext;

import javax.servlet.jsp.JspException;


/**
 * The BodyTag interface extends IterationTag by defining additional methods
 * that let a tag handler manipulate the content of evaluating its body.
 * <p/>
 * It is the responsibility of the tag handler to manipulate the body content.
 * For example the tag handler may take the body content, convert it into a
 * String using the bodyContent.getString method and then use it. Or the tag
 * handler may take the body content and write it out into its enclosing
 * JspWriter using the bodyContent.writeOut method.
 * <p/>
 * A tag handler that implements BodyTag is treated as one that implements
 * IterationTag, except that the doStartTag method can return SKIP_BODY,
 * EVAL_BODY_INCLUDE or EVAL_BODY_BUFFERED.
 * <p/>
 * If EVAL_BODY_INCLUDE is returned, then evaluation happens as in IterationTag.
 * <p/>
 * If EVAL_BODY_BUFFERED is returned, then a BodyContent object will be created
 * (by code generated by the JSP compiler) to capture the body evaluation. The
 * code generated by the JSP compiler obtains the BodyContent object by calling
 * the pushBody method of the current pageContext, which additionally has the
 * effect of saving the previous out value. The page compiler returns this
 * object by calling the popBody method of the PageContext class; the call also
 * restores the value of out.
 * <p/>
 * The interface provides one new property with a setter method and one new
 * action method.
 * <p/>
 * <B>Properties</B>
 * <p/>
 * There is a new property: bodyContent, to contain the BodyContent object,
 * where the JSP Page implementation object will place the evaluation (and
 * reevaluation, if appropriate) of the body. The setter method (setBodyContent)
 * will only be invoked if doStartTag() returns EVAL_BODY_BUFFERED and the
 * corresponding action element does not have an empty body.
 * <p/>
 * <B>Methods</B>
 * <p/>
 * In addition to the setter method for the bodyContent property, there is a new
 * action method: doInitBody(), which is invoked right after setBodyContent()
 * and before the body evaluation. This method is only invoked if doStartTag()
 * returns EVAL_BODY_BUFFERED.
 * <p/>
 * <B>Lifecycle</B>
 * <p/>
 * Lifecycle details are described by the transition diagram below. Exceptions
 * that are thrown during the computation of doStartTag(), setBodyContent(),
 * doInitBody(), BODY, doAfterBody() interrupt the execution sequence and are
 * propagated up the stack, unless the tag handler implements the
 * TryCatchFinally interface; see that interface for details.
 * <p/>
 * <IMG src="doc-files/BodyTagProtocol.gif"
 * alt="Lifecycle Details Transition Diagram for BodyTag"/>
 * <p/>
 * <B>Empty and Non-Empty Action</B>
 * <p/>
 * If the TagLibraryDescriptor file indicates that the action must always have
 * an empty element body, by an &lt;body-content&gt; entry of "empty", then the
 * doStartTag() method must return SKIP_BODY. Otherwise, the doStartTag() method
 * may return SKIP_BODY, EVAL_BODY_INCLUDE, or EVAL_BODY_BUFFERED.
 * <p/>
 * Note that which methods are invoked after the doStartTag() depends on both
 * the return value and on if the custom action element is empty or not in the
 * JSP page, not how it's declared in the TLD.
 * <p/>
 * If SKIP_BODY is returned the body is not evaluated, and doEndTag() is
 * invoked.
 * <p/>
 * If EVAL_BODY_INCLUDE is returned, and the custom action element is not empty,
 * setBodyContent() is not invoked, doInitBody() is not invoked, the body is
 * evaluated and "passed through" to the current out, doAfterBody() is invoked
 * and then, after zero or more iterations, doEndTag() is invoked. If the custom
 * action element is empty, only doStart() and doEndTag() are invoked.
 * <p/>
 * If EVAL_BODY_BUFFERED is returned, and the custom action element is not
 * empty, setBodyContent() is invoked, doInitBody() is invoked, the body is
 * evaluated, doAfterBody() is invoked, and then, after zero or more iterations,
 * doEndTag() is invoked. If the custom action element is empty, only doStart()
 * and doEndTag() are invoked.
 */
public interface BodyTag extends IterationTag {

	/**
	 * Deprecated constant that has the same value as EVAL_BODY_BUFFERED and
	 * EVAL_BODY_AGAIN. This name has been marked as deprecated to encourage the
	 * use of the two different terms, which are much more descriptive.
	 *
	 * @deprecated As of Java JSP API 1.2, use BodyTag.EVAL_BODY_BUFFERED or
	 * IterationTag.EVAL_BODY_AGAIN.
	 */
	@SuppressWarnings("dep-ann")
	// TCK signature test fails with annotation
	public static final int EVAL_BODY_TAG = 2;

	/**
	 * Request the creation of new buffer, a BodyContent on which to evaluate
	 * the body of this tag. Returned from doStartTag when it implements
	 * BodyTag. This is an illegal return value for doStartTag when the class
	 * does not implement BodyTag.
	 */
	public static final int EVAL_BODY_BUFFERED = 2;

	/**
	 * Set the bodyContent property. This method is invoked by the JSP page
	 * implementation object at most once per action invocation. This method
	 * will be invoked before doInitBody. This method will not be invoked for
	 * empty tags or for non-empty tags whose doStartTag() method returns
	 * SKIP_BODY or EVAL_BODY_INCLUDE.
	 * <p/>
	 * When setBodyContent is invoked, the value of the implicit object out has
	 * already been changed in the pageContext object. The BodyContent object
	 * passed will have not data on it but may have been reused (and cleared)
	 * from some previous invocation.
	 * <p/>
	 * The BodyContent object is available and with the appropriate content
	 * until after the invocation of the doEndTag method, at which case it may
	 * be reused.
	 *
	 * @param b the BodyContent
	 * @see #doInitBody
	 * @see #doAfterBody
	 */
	void setBodyContent(BodyContent b);

	/**
	 * Prepare for evaluation of the body. This method is invoked by the JSP
	 * page implementation object after setBodyContent and before the first time
	 * the body is to be evaluated. This method will not be invoked for empty
	 * tags or for non-empty tags whose doStartTag() method returns SKIP_BODY or
	 * EVAL_BODY_INCLUDE.
	 * <p/>
	 * The JSP container will resynchronize the values of any AT_BEGIN and
	 * NESTED variables (defined by the associated TagExtraInfo or TLD) after
	 * the invocation of doInitBody().
	 *
	 * @throws JspException if an error occurred while processing this tag
	 * @see #doAfterBody
	 */
	void doInitBody() throws JspException;
}
