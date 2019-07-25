/** 
 * If vec is a StringVector representing an expression, then this        returns the StringVector obtained by parenthesizing the expression    if it may need parenthesizing.  This is used only to prevent          parsing errors when the expression appears immediately to the right   of an "=" in the spec.  This is a rare situation, so it would be      nice to add the parentheses only if really necessary.  For now, the   parentheses are added if one of the following tokens occur outside    parentheses and not inside a string:                                  * =                                                                   #                                                                   <  not followed by <                                                >  not followed by > or preceded by =                               |  preceded or followed by -                                        \  not followed by "o" or "X".                                      /  followed by "\"                                                  * Left parentheses are                                                  * (  [  {  <<                                                         * The handling of "\" is a simplifying hack.  Lots of operators         beginning with "\" like "\/", "\gg" and "\subseteq" have precedence   greater than or equal to "=".  The only commonly used ones with       precedence lower than "=" seem to be "\o" and "\X".  It doesn't       seem to be worth the bother of checking for the others just to        avoid unnecessarily adding the parentheses when those other rare      operators are used.                                                   * Perhaps the one improvement that might be worth making in this        procedure is to have it not add parentheses because of "dangerous"    operations in an IF clause--for example:                              * IF x < 0 THEN ...                                                * This would require considering "IF" to be a left parenthesis and      "THEN" to be a right parenthesis.  However, that's not trivial to     implement because of unlikely things like                             * IFx := 42 ;                                                       x := IFx < THENx                                                 
 */
private static Vector Parenthesize(Vector vec){
  if (NeedsParentheses(vec)) {
    vec.setElementAt("(" + ((String)vec.elementAt(0)),0);
    for (int i=1; i < vec.size(); i++) {
      vec.setElementAt(" " + ((String)vec.elementAt(i)),i);
    }
    ;
    int curLineNum=vec.size() - 1;
    vec.setElementAt(((String)vec.elementAt(curLineNum)) + ")",curLineNum);
  }
  ;
  return vec;
}
