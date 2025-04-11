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
 * The formula consisting of a label.
 *
 * @author Amgad Rady
 * @author Franck van Breugel
 */
public class Label extends Formula {
	private String label;

	/**
	 * Initializes this formula representing a label with the given string.
	 *
	 * @param label the label
	 */
	public Label(String label) {
		super(true);
		this.label = label;
	}

	/**
	 * Returns the label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return this.label;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Formula simplify() {
		return this;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean smallerOrEqual(Formula other) {
		if (other instanceof Label) {
			if (this.equals(other)) {
				return true;
			}
		} else if (other instanceof Plus) {
			Plus plus = (Plus) other;
			return this.smallerOrEqual(plus.getSubformula());
		}
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean greaterOrEqual(Formula other) {
		if (other instanceof Label) {
			if (this.equals(other)) {
				return true;
			}
		} else if (other instanceof Minus) {
			Minus minus = (Minus) other;
			return this.greaterOrEqual(minus);
		}
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toLaTeX() {
		return this.label;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object object) {
		return object != null && object instanceof Label && this.label.equals(((Label) object).label);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return this.label.hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return this.label;
	}
}
