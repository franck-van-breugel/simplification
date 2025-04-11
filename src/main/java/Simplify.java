/*
 * Copyright (C)  2025  Franck van Breugel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import logic.Formula;
import logic.FormulaLexer;
import logic.FormulaParser;
import logic.Generator;

import java.util.Scanner;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * Simplifies a formula.
 * 
 * @author Franck van Breugel
 */
public class Simplify {
	private Simplify() {}
	
	private static final String PROMPT = "This app simplifies a formula.\n\nFormulas are defined by the following grammar:\n  f ::= true | false | label | (f) | f - r | f + r | O[f] | f && f | f || f\nwhere label consists of one or more lowercase letters and r is a real number.\n\nProvide a formula: "; 
	
	/**
	 * Prompts user to enter a formula and prints its simplification. 
	 */
	public static void main(String[] args) {
		// prompt the user
		System.out.print(PROMPT);
		
		// read the formula
		Scanner input = new Scanner(System.in);
		String description = input.nextLine();
		
		// parse the formula
		FormulaParser parser = new FormulaParser(new CommonTokenStream(new FormulaLexer(CharStreams.fromString(description))));
		ParseTree tree = parser.formula();
		Formula formula = new Generator().visit(tree);

		// simplify the formula
		Formula simplified = formula.simplify();
		
		// print the simplified formula
		System.out.print("\nThe formula " + formula + "\nis simplified to " + simplified);
	}
}
