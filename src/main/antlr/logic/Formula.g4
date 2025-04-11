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
 * along with this program.  If not, see https://www.gnu.org/licenses/.
 *
 * @author Franck van Breugel
 */

grammar Formula;

@header {
  package logic;
}

/* Formulas */

formula
    : '(' formula ')' # Bracket
    | 'true' # True
    | 'false' # False
    | LABEL # Label
    | formula '-' REAL # Minus
    | formula '+' REAL # Plus
    | 'O[' formula ']' # Next
    | <assoc=left> formula '&&' formula # And
    | <assoc=left> formula '||' formula # Or
    ;

/* Labels */

LABEL : [a-z]+; 

/* Real numbers */

REAL: [0-9]+ ('.' [0-9]+)?;

/* Skip white space */

WS : [ \t\r\n\u000C]+ -> skip ; 
