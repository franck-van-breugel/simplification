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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The disjunction formula.
 *
 * @author Amgad Rady
 * @author Franck van Breugel
 */
public class Or extends Formula {
	private Formula left;
	private Formula right;

	/**
	 * Initializes this formula with the given left and right formula.
	 * 
	 * @param left left subformula
	 * @param right right subformula
	 */
	public Or(Formula left, Formula right) {
		this(left, right, false);
	}

	/**
	 * Initializes this formula with the given left and right formula.
	 * 
	 * @param left left subformula
	 * @param right right subformula
	 * @param simplified whether this formula is simplified
	 */
	public Or(Formula left, Formula right, boolean simplified) {
		super(simplified);
		this.left = left;
		this.right = right;
	}

	/**
	 * Returns the left subformula.
	 *
	 * @return the left subformula
	 */
	public Formula getLeft() {
		return this.left;
	}
	
	/**
	 * Returns the right subformula.
	 *
	 * @return the right subformula
	 */
	public Formula getRight() {
		return this.right;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Formula simplify() {
		Formula simplifiedLeft = this.left.simplified ? this.left : this.left.simplify();
		Formula simplifiedRight = this.right.simplified ? this.right : this.right.simplify();

		// false || g = g
		if (simplifiedLeft instanceof False) {
			return simplifiedRight;
		}
		
		// f || false = f
		if (simplifiedRight instanceof False) {
			return simplifiedLeft;
		}
		
		// true || g = true
		if (simplifiedLeft instanceof True) {
			return simplifiedLeft;
		}
		
		// f || true = true
		if (simplifiedRight instanceof True) {
			return simplifiedRight;
		}
		
		// if f <= g then f || g = g
		if (simplifiedLeft.smallerOrEqual(simplifiedRight)) {
			return simplifiedRight;
		}
		
		// if g <= f then f || g = f
		if (simplifiedRight.smallerOrEqual(simplifiedLeft)) {
			return simplifiedLeft;
		}

		return new Or(simplifiedLeft, simplifiedRight, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean smallerOrEqual(Formula other) {
		return this.left.smallerOrEqual(other) && this.right.smallerOrEqual(other);	
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean greaterOrEqual(Formula other) {
		return this.left.greaterOrEqual(other) || this.right.greaterOrEqual(other);	
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toLaTeX() {
		return "(" + this.left.toLaTeX() + " \\vee " + this.right.toLaTeX() + ")";
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object object) {		
		if (object != null && object instanceof Or) {
			Or other = (Or) object;
			return this.left.equals(other.left) && this.right.equals(other.right);

		} else {
			return false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		final int PRIME = 37;
		return this.left.hashCode() + PRIME * this.right.hashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "(" + this.left + " || " + this.right + ")";
	}
}
