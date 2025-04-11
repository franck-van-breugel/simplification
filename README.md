Overview
--------

Desharnais, Gupta, Jagadeesan, and Panangaden [DGJP99] introduced a logic to characterized the probabilistic bisimilarity distances, a quantitative generalization of probabilistic bisimilarity due to Larsen and Skou [LS89].  Here we consider a variant of their logic, also characterizing the probabilistic bisimilarity distances, that has been studied by Rady and Van Breugel in [RB23].  The syntax of the logic is defined by the following grammar.

f ::= true | false | l | (f) | f - r | f + r | O[f] | f && f | f || f

where l consists of one or more lowercase letters and r is a real number.

Given a state s of a labelled Markov process and a formula f of the logic, the interpretation of f in s, denoted `[[f]](s)`, is a real number in the interval [0, 1] that roughly captures the probability that formula f is satisfied in state s.  
Formulas f and g are equivalent if `[[f]](s) = [[g]](s)` for all states s.  For example, the formula

O[((O[((a + 0) && (a - 1 + 1)) || (a - 1 + 0)] - 0 + 0) && (O[((a + 0) && (a - 1 + 1)) || (a - 1 + 0)] - 0 + 0) && (a - 1 + 1)) || ((O[((a + 0) && (a - 1 + 1)) || (a - 1 + 0)] - 0 + 0) && (O[((a + 0) && (a - 1 + 1)) || (a - 1 + 0)] - 0 + 0) && (a - 1 + 1)) || (a - 1 + 0) || (a - 1 + 0)] - 0.375

is equivalent to O[O[a]] - 0.375 and the formula

O[((O[((b + 0) && (b + 0) && (b + 0) && (a - 1 + 1)) || (a - 1 + 0) || (a - 1 + 0) || (a - 1 + 0)] - 0 + 0) && (O[((b + 0) && (b + 0) && (b + 0) && (a - 1 + 1)) || (a - 1 + 0) || (a - 1 + 0) || (a - 1 + 0)] - 0 + 0) && (O[((b + 0) && (b + 0) && (b + 0) && (a - 1 + 1)) || (a - 1 + 0) || (a - 1 + 0) || (a - 1 + 0)] - 0 + 0) && (a - 1 + 0.5)) || (a - 1 + 0) || (a - 1 + 0) || (a - 1 + 0)] - 0

is equivalent to O[(O[b] && (false + 0.5))].  Smaller formulas are easier to analyze.  The app Simplify takes as input a formula and produces as output an equivalent formula that may be significantly smaller (but not neccessarily a smallest equivalent formula).

[DGJP99] Josee Desharnais, Vineet Gupta, Radha Jagadeesan, and Prakash Panangaden. Metrics for labeled Markov systems. In Jos Baeten and Sjouke Mauw, editors, *Proceedings of 10th International Conference on Concurrency Theory*, volume 1664 of *Lecture Notes in Computer Science*, pages 258–273, Eindhoven, the Netherlands, August 1999. Springer-Verlag.  

[LS89] Kim Larsen and Arne Skou. Bisimulation through probabilistic testing. In *Proceedings of the 16th Annual ACM Symposium on Principles of Programming Languages*, pages 344-352, Austin, TX, USA, January 1989. ACM.  

[RB23] Amgad Rady and Franck van Breugel. Explainability of probabilistic bisimilarity distances for labelled Markov chains. In Orna Kupferman and Pawel Sobocinski, editors, *Proceedings of the 26th International Conference on Foundations of Software Science and Computational Structures*, volume 13992 of *Lecture Notes in Computer Science*, pages 285–307, Paris, France, April 2023. Springer-Verlag.  

Licensing
---------

This free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This extension is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You can find a copy of the GNU General Public License at http://www.gnu.org/licenses

Installing 
----------

To install the code, you need
- Git
- Java

Assuming that you want install the code in the folder C:/Users/Franck/Documents/simplification

1. Go to the folder: ```cd C:/Users/Franck/Documents/```
2. In that folder, clone the repository: ```git clone https://github.com/franck-van-breugel/simplification.git```
3. Go to the folder: ```cd C:/Users/Franck/Documents/simplification/```
3. In that folder, run the gradle wrapper: ```./gradlew build```

Running
-------

To run the Simplify app use

```java -cp C:/Users/Franck/Documents/simplification/build/libs/simplification-all.jar Simplify```

Questions/comments/suggestions
------------------------------

Please email them to franck@yorku.ca

Thanks
------

to Amgad Rady for his help with the development.
