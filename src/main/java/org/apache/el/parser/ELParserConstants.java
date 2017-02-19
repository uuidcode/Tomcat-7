/* Generated By:JJTree&JavaCC: Do not edit this line. ELParserConstants.java */
package org.apache.el.parser;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface ELParserConstants {

	/** End of File. */
	int EOF = 0;
	/** RegularExpression Id. */
	int LITERAL_EXPRESSION = 1;
	/** RegularExpression Id. */
	int START_DYNAMIC_EXPRESSION = 2;
	/** RegularExpression Id. */
	int START_DEFERRED_EXPRESSION = 3;
	/** RegularExpression Id. */
	int INTEGER_LITERAL = 8;
	/** RegularExpression Id. */
	int FLOATING_POINT_LITERAL = 9;
	/** RegularExpression Id. */
	int EXPONENT = 10;
	/** RegularExpression Id. */
	int STRING_LITERAL = 11;
	/** RegularExpression Id. */
	int TRUE = 12;
	/** RegularExpression Id. */
	int FALSE = 13;
	/** RegularExpression Id. */
	int NULL = 14;
	/** RegularExpression Id. */
	int END_EXPRESSION = 15;
	/** RegularExpression Id. */
	int DOT = 16;
	/** RegularExpression Id. */
	int LPAREN = 17;
	/** RegularExpression Id. */
	int RPAREN = 18;
	/** RegularExpression Id. */
	int LBRACK = 19;
	/** RegularExpression Id. */
	int RBRACK = 20;
	/** RegularExpression Id. */
	int COLON = 21;
	/** RegularExpression Id. */
	int COMMA = 22;
	/** RegularExpression Id. */
	int GT0 = 23;
	/** RegularExpression Id. */
	int GT1 = 24;
	/** RegularExpression Id. */
	int LT0 = 25;
	/** RegularExpression Id. */
	int LT1 = 26;
	/** RegularExpression Id. */
	int GE0 = 27;
	/** RegularExpression Id. */
	int GE1 = 28;
	/** RegularExpression Id. */
	int LE0 = 29;
	/** RegularExpression Id. */
	int LE1 = 30;
	/** RegularExpression Id. */
	int EQ0 = 31;
	/** RegularExpression Id. */
	int EQ1 = 32;
	/** RegularExpression Id. */
	int NE0 = 33;
	/** RegularExpression Id. */
	int NE1 = 34;
	/** RegularExpression Id. */
	int NOT0 = 35;
	/** RegularExpression Id. */
	int NOT1 = 36;
	/** RegularExpression Id. */
	int AND0 = 37;
	/** RegularExpression Id. */
	int AND1 = 38;
	/** RegularExpression Id. */
	int OR0 = 39;
	/** RegularExpression Id. */
	int OR1 = 40;
	/** RegularExpression Id. */
	int EMPTY = 41;
	/** RegularExpression Id. */
	int INSTANCEOF = 42;
	/** RegularExpression Id. */
	int MULT = 43;
	/** RegularExpression Id. */
	int PLUS = 44;
	/** RegularExpression Id. */
	int MINUS = 45;
	/** RegularExpression Id. */
	int QUESTIONMARK = 46;
	/** RegularExpression Id. */
	int DIV0 = 47;
	/** RegularExpression Id. */
	int DIV1 = 48;
	/** RegularExpression Id. */
	int MOD0 = 49;
	/** RegularExpression Id. */
	int MOD1 = 50;
	/** RegularExpression Id. */
	int IDENTIFIER = 51;
	/** RegularExpression Id. */
	int FUNCTIONSUFFIX = 52;
	/** RegularExpression Id. */
	int IMPL_OBJ_START = 53;
	/** RegularExpression Id. */
	int LETTER = 54;
	/** RegularExpression Id. */
	int DIGIT = 55;
	/** RegularExpression Id. */
	int ILLEGAL_CHARACTER = 56;

	/** Lexical state. */
	int DEFAULT = 0;
	/** Lexical state. */
	int IN_EXPRESSION = 1;

	/** Literal token values. */
	String[] tokenImage = {
			"<EOF>",
			"<LITERAL_EXPRESSION>",
			"\"${\"",
			"\"#{\"",
			"\" \"",
			"\"\\t\"",
			"\"\\n\"",
			"\"\\r\"",
			"<INTEGER_LITERAL>",
			"<FLOATING_POINT_LITERAL>",
			"<EXPONENT>",
			"<STRING_LITERAL>",
			"\"true\"",
			"\"false\"",
			"\"null\"",
			"\"}\"",
			"\".\"",
			"\"(\"",
			"\")\"",
			"\"[\"",
			"\"]\"",
			"\":\"",
			"\",\"",
			"\">\"",
			"\"gt\"",
			"\"<\"",
			"\"lt\"",
			"\">=\"",
			"\"ge\"",
			"\"<=\"",
			"\"le\"",
			"\"==\"",
			"\"eq\"",
			"\"!=\"",
			"\"ne\"",
			"\"!\"",
			"\"not\"",
			"\"&&\"",
			"\"and\"",
			"\"||\"",
			"\"or\"",
			"\"empty\"",
			"\"instanceof\"",
			"\"*\"",
			"\"+\"",
			"\"-\"",
			"\"?\"",
			"\"/\"",
			"\"div\"",
			"\"%\"",
			"\"mod\"",
			"<IDENTIFIER>",
			"<FUNCTIONSUFFIX>",
			"\"#\"",
			"<LETTER>",
			"<DIGIT>",
			"<ILLEGAL_CHARACTER>",
	};

}
