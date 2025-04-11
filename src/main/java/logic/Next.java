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

/**
 * The next formula.
 *
 * @author Amgad Rady
 * @author Franck van Breugel
 */
public class Next extends Formula {
	private Formula subformula;

	/**
	 * Initializes this next formula with the given subformula.
	 *
	 * @param subformula the subformula
	 */
	public Next(Formula subformula) {
		this(subformula, false);
	}

	/**
	 * Initializes this next formula with the given subformula.
	 *
	 * @param subformula the subformula
	 * @param simplified whether this formula is simplified
	 */
	public Next(Formula subformula, boolean simplified) {
		super(simplified);
		this.subformula = subformula;
	}

	/**
	 * Returns the subformula of this next formula.
	 *
	 * @return the subformula of this next formula
	 */
	public Formula getSubformula() {
		return this.subformula;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Formula simplify() {
		// if f = g then X f = X g
		Formula simplified = this.subformula.simplified ? this.subformula : this.subformula.simplify();
		if (simplified instanceof False) {
			// X false = false
			return simplified;
		} else if (simplified instanceof True) {
			// X true = true
			return simplified;
		} else {
			return new Next(simplified, true); 
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean smallerOrEqual(Formula other) {
		if (other instanceof Next) {
			Formula otherSubformula = ((Next) other).getSubformula();
			if (this.subformula.smallerOrEqual(otherSubformula)) {
				// if f <= g then X f <= X g
				return true; 
			}
		} 
		return false;		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean greaterOrEqual(Formula other) {
		if (other instanceof Next) {
			Formula otherSubformula = ((Next) other).getSubformula();
			if (this.subformula.greaterOrEqual(otherSubformula)) {
				// if f >= g then X f >= X g
				return true; 
			}
		} 
		return false;		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toLaTeX() {
		return "(\\bigcirc " + this.subformula.toLaTeX() + ")";
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object object) {
		if (object != null && this.getClass() == object.getClass()) {
			Next next = (Next) object;
			return this.subformula.equals(next.subformula);
		} else {
			return false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return this.subformula.hashCode() + 1;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "O[" + this.subformula.toString() + "]";
	}
}
