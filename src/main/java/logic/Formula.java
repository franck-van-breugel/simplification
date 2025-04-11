/*
 * Copyright (C)  2020  Amgad Rady and Franck van Breugel
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

import java.util.Random;

/**
 * A formula.
 *
 * @author Amgad Rady
 * @author Franck van Breugel
 */
public abstract class Formula {
	protected boolean simplified;

	/**
	 * Desired accuracy.
	 */
	public static final double ACCURACY = 1E-12;
	
	/**
	 * Initializes this formula.
	 */
	public Formula() {
		this.simplified = false;
	}
	
	/**
	 * Initializes this formula.
	 * 
	 * @param simplified whether this formula is simplified
	 */
	public Formula(boolean simplified) {
		this.simplified = simplified;
	}
	
	/**
	 * Returns a simplification of this formula that is semantically equivalent to this formula.
	 *
	 * @return a simplification of this formula
	 */
	public abstract Formula simplify();

	/**
	 * Returns a LaTeX representation of this formula.
	 *
	 * @return a LaTeX representation of this formula
	 */
	public abstract String toLaTeX();
	
	/**
	 * Tests whether this formula is smaller than or equal to the other given formula.  
	 * If the method returns true, then this formula is smaller than or equal to the other given formula.
	 * Even if the method returns false, then formula may be smaller than or equal to the other given formula.
	 * 
	 * @param other a formula
	 * @pre. other != null
	 * @return approximation of whether this formula is smaller than or equal to the other given formula
	 */
	public abstract boolean smallerOrEqual(Formula other);
	
	/**
	 * Tests whether this formula is greater than or equal to the other given formula.  
	 * If the method returns true, then this formula is greater than or equal to the other given formula.
	 * Even if the method returns false, then formula may be greater than or equal to the other given formula.
	 * 
	 * @param other a formula
	 * @pre. other != null
	 * @return approximation of whether this formula is greater than or equal to the other given formula
	 */
	public abstract boolean greaterOrEqual(Formula other);
	
	/**
	 * Returns the hash code of this formula.
	 * 
	 * @return the hash code of this formula
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	/**
	 * Tests whether this formula is syntactically equivalent to the given object.
	 * 
	 * @param object an object
	 * @return true if this formula is syntactically equivalent to the given object,
	 * false otherwise.
	 */
	@Override
	public boolean equals(Object object) {	
		return super.equals(object);
	}
	
	/**
	 * Returns a string representation of this formula.
	 *
	 * @return a string representation of this formula
	 */
	@Override
	public String toString() {
		return super.toString();
	}
	
	/**
	 * Randomness.
	 */
	private static final Random RANDOM = new Random();
	
	/**
	 * Returns a random formula of at most the given depth.
	 * 
	 * @param depth maximum depth of the formula
	 * @return a random formula of at most the given depth
	 */
	public static Formula random(int depth) {
		final int BASE_CASES = 3;
		final int INDUCTIVE_CASES = 5;
		final int MAX_SIZE = 5;
		final int MAX_LENGTH = 3;
		final int LETTERS = 26;
		
		if (depth == 0) {
			switch (RANDOM.nextInt(BASE_CASES)) {
			case 0 : return new True();
			case 1 : return new False();
			case 2 : int length = 1 + RANDOM.nextInt(MAX_LENGTH);
				String label = "";
				while (length > 0) {
					char letter = (char) ('a' + RANDOM.nextInt(LETTERS));
					label += letter;
					length--;
				}
				return new Label(label);
			default : throw new IllegalArgumentException("Something went wrong with creating a random formula");
			}
		} else {
			switch (RANDOM.nextInt(BASE_CASES + INDUCTIVE_CASES)) {
			case 0 : 
				return new True();
			case 1 : 
				return new False();
			case 2 : 
				int length = 1 + RANDOM.nextInt(MAX_LENGTH);
				String label = "";
				while (length > 0) {
					char letter = (char) ('a' + RANDOM.nextInt(LETTERS));
					label += letter;
					length--;
				}
				return new Label(label);
			case 3 : 
				return new Next(random(depth - 1));
			case 4 : 
				return new Minus(random(depth - 1), RANDOM.nextDouble());
			case 5 : 
				return new Plus(random(depth - 1), RANDOM.nextDouble());
			case 6 : 
				Formula left = random(depth - 1);
				Formula right = random(depth - 1);
				return new Or(left, right, false);
			case 7 : 
				left = random(depth - 1);
				right = random(depth - 1);
				return new And(left, right, false);
			default : throw new IllegalArgumentException("Something went wrong with creating a random formula");
			}
		}
	}
}
