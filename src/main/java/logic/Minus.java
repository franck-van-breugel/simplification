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
 * The formula shifted by some negative amount.
 *
 * @author Amgad Rady
 * @author Franck van Breugel
 */
public class Minus extends Formula {
	private Formula subformula;
	private double shift;

	/**
	 * Initializes this formula consisting of the given subformula shifted by
	 * the given amount negatively (subtracting).
	 *
	 * @param subformula a formula
	 * @param shift the shift amount
	 * @pre. shift in [0, 1]
	 */
	public Minus(Formula subformula, double shift) {
		this(subformula, shift, false);
	}

	/**
	 * Initializes this formula consisting of the given subformula shifted by
	 * the given amount negatively (subtracting).
	 *
	 * @param subformula a formula
	 * @param shift the shift amount
	 * @pre. shift in [0, 1]
	 * @param whether this formula is simplified
	 */
	public Minus(Formula subformula, double shift, boolean simplified) {
		super(simplified);
		this.subformula = subformula;
		this.shift = shift;
	}

	/**
	 * Returns the subformula of this formula.
	 *
	 * @return the subformula of this formula
	 */
	public Formula getSubformula() {
		return this.subformula;
	}

	/**
	 * Returns the shift amount.
	 *
	 * @return the shift amount
	 */
	public double getShift() {
		return this.shift;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Formula simplify() {
		Formula simplifiedFormula = this.subformula.simplified ? this.subformula : this.subformula.simplify();
		if (simplifiedFormula instanceof False) {
			// false - q = false
			return simplifiedFormula;
		} else if (this.shift < ACCURACY) {
			// f - 0 = f
			return simplifiedFormula;
		} else if (this.shift > 1.0 - ACCURACY) {
			// f - 1 = false
			return new False();
		} else {
			// if f = f' then f - q = f' - q
			return new Minus(simplifiedFormula, this.shift, true);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean smallerOrEqual(Formula other) {
		if (this.subformula instanceof Plus) {
			Plus plus = (Plus) this.subformula;
			// if f <= g and q >= p then f + p - q <= g
			if (this.shift >= plus.getShift() && plus.getSubformula().smallerOrEqual(other)) {
				return true;
			}
		}
		if (other instanceof Minus) {
			// if f <= f' and q >= q' then f - q <= f' - q'
			double otherShift = ((Minus) other).getShift();
			Formula otherSubformula = ((Minus) other).getSubformula();
			if (this.subformula.smallerOrEqual(otherSubformula) && this.shift >= otherShift) {
				return true; 
			}
		} 
		if (other instanceof Plus) {
			double otherShift = ((Plus) other).getShift();
			Formula otherSubformula = ((Plus) other).getSubformula();
			// if q >= 1 - q' then f - q <= false + q'
			if (otherSubformula instanceof False && this.shift >= 1 - otherShift) {
				return true; 
			}
		}
		// if f <= g then f - q <= g
		if (this.subformula.smallerOrEqual(other)) {
			return true; 
		}
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean greaterOrEqual(Formula other) {
		if (other instanceof Minus) {
			// if f >= f' and q <= q' then f - q >= f' - q'
			double otherShift = ((Minus) other).getShift();
			Formula otherSubformula = ((Minus) other).getSubformula();
			if (this.subformula.greaterOrEqual(otherSubformula) && this.shift <= otherShift) {
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
		return "(" + this.subformula.toLaTeX() + " \\ominus " + this.shift +  ")";
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object object) {
		if (object != null && this.getClass() == object.getClass()) {
			Minus other = (Minus) object;
			return this.subformula.equals(other.subformula) && Math.abs(this.shift - other.shift) < ACCURACY;
		} else {
			return false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return this.subformula.hashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "(" + this.subformula + " - " + this.shift + ")";
	}
}
