/*
 * Copyright (C)  2025
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

package logic;

import logic.FormulaBaseVisitor;
import logic.FormulaParser.AndContext;
import logic.FormulaParser.LabelContext;
import logic.FormulaParser.BracketContext;
import logic.FormulaParser.FalseContext;
import logic.FormulaParser.LabelContext;
import logic.FormulaParser.MinusContext;
import logic.FormulaParser.NextContext;
import logic.FormulaParser.OrContext;
import logic.FormulaParser.PlusContext;
import logic.FormulaParser.TrueContext;

/**
 * Generates an abstract syntax tree from a parse tree.
 * 
 * @author Franck van Breugel
 */
public class Generator extends FormulaBaseVisitor<Formula> {
	
	/**
	 * Visits the given Bracket node in the parse tree and returns the abstract syntax
	 * tree corresponding to the subtree of the parse tree rooted at the Bracket node.
	 * 
	 * @param context a node in the syntax tree that corresponds to the Bracket
	 * @return A {@code Formula} instance that represents abstract syntax tree corresponding 
	 * to the formula within the brackets 
	 */
	@Override
	public Formula visitBracket(BracketContext context) {
		return visit(context.formula());
	}

	/**
	 * Visits the given True Terminal node in the parse tree 
	 * 
	 * @param context a node in the syntax tree that corresponds to the True alternative
	 * @return A {@code True} instance 
	 */
	@Override
	public Formula visitTrue(TrueContext context) {
		return new True();
	}

	/**
	 * Visits the given False Terminal node in the parse tree 
	 * 
	 * @param context a node in the syntax tree that corresponds to the False alternative
	 * @return A {@code False} instance 
	 */	
	@Override
	public Formula visitFalse(FalseContext context) {
		return new False();
	}
	
	/**
	 * Visits the given Label Terminal node in the parse tree and return the context of atomic proposition 
	 * 
	 * @param context a node in the syntax tree that corresponds to the Label alternative
	 * @return A {@code Label} instance containing a string representation of a label as defined by the grammar
	 */	
	@Override
	public Formula visitLabel(LabelContext context) {
		return new Label(context.LABEL().toString());
	}
	
	/**
	 * Visits the given Minus node in the parse tree and returns the abstract syntax
	 * tree corresponding to the subtree of the parse tree rooted at the Minus node.
	 * 
	 * @param context a node in the syntax tree that corresponds to the Minus alternative
	 * @return A {@code Minus} instance that represents abstract syntax tree corresponding 
	 * to the subtree of the parse tree rooted {@code context}
	 */
	@Override
	public Formula visitMinus(MinusContext context) {
		Formula formula = (Formula) visit(context.formula());
		double shift = Double.parseDouble(context.REAL().toString());
		return new Minus(formula, shift);
	}
	
	/**
	 * Visits the given Plus node in the parse tree and returns the abstract syntax
	 * tree corresponding to the subtree of the parse tree rooted at the Plus node.
	 * 
	 * @param context a node in the syntax tree that corresponds to the Plus alternative
	 * @return A {@code Plus} instance that represents abstract syntax tree corresponding 
	 * to the subtree of the parse tree rooted {@code context}
	 */
	@Override
	public Formula visitPlus(PlusContext context) {
		Formula formula = (Formula) visit(context.formula());
		double shift = Double.parseDouble(context.REAL().toString());
		return new Plus(formula, shift);
	}

	/**
	 * Visits the given Next node in the parse tree and returns the abstract syntax
	 * tree corresponding to the subtree of the parse tree rooted at the Next node.
	 * 
	 * @param context a node in the syntax tree that corresponds to the Next alternative
	 * @return A {@code Next} instance that represents abstract syntax tree corresponding 
	 * to the subtree of the parse tree rooted {@code context}
	 */
	@Override
	public Formula visitNext(NextContext context) {
		Formula formula = (Formula) visit(context.formula());
		return new Next(formula);
	}
	
	/**
	 * Visits the left and right sub trees of the given And node in the parse tree and returns
	 * an And instance containing the left and right abstract syntax trees 
	 * 
	 * @param context a node in the syntax tree that corresponds to the And 
	 * @return An {@code And} instance that represents abstract syntax tree corresponding 
	 * to the left and right subtree of the parse tree rooted {@code context}
	 */	
	@Override
	public Formula visitAnd(AndContext context) {
		Formula left = (Formula) visit(context.formula(0));
		Formula right = (Formula) visit(context.formula(1));
		return new And(left, right, false);
	}
	
	/**
	 * Visits the left and right sub trees of the given Or node in the parse tree and returns
	 * an Or instance containing the left and right abstract syntax trees 
	 * 
	 * @param context a node in the syntax tree that corresponds to the Or 
	 * @return An {@code Or} instance that represents abstract syntax tree corresponding 
	 * to the left and right subtree of the parse tree rooted {@code context}
	 */	
	@Override
	public Formula visitOr(OrContext context) {
		Formula left = (Formula) visit(context.formula(0));
		Formula right = (Formula) visit(context.formula(1));
		return new Or(left, right, false);
	}
}