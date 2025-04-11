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
 * The formula true.
 *
 * @author Amgad Rady
 * @author Franck van Breugel
 */
public class True extends Formula {
	/**
	 * Initializes this formula.
	 */
	public True() {
		super(true);
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
		return other instanceof True;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean greaterOrEqual(Formula other) {
		return true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toLaTeX() {
		return "\\mathrm{true}";
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object object) {
		return object != null && object instanceof True;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return 0;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "true";
	}
}
