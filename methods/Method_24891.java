/** 
 * Looks for the pattern at a specified target scope depth relative to the scope depth of the starting position. Example: Calling this with starting position inside a method body and target depth 0 would search only in the method body, while using target depth -1 would look only in the body of the enclosing class (but not in any methods of the class or outside of the class). By using a scope range, you can e.g. search in the whole class including bodies of methods and inner classes.
 * @param pattern matching is realized by find() method of this pattern
 * @param code Java code without comments
 * @param start starting position in the code String (inclusive)
 * @param stop ending position in the code Sting (exclusive)
 * @param minTargetScopeDepth desired min scope depth of the match relative to thescope of the starting position
 * @param maxTargetScopeDepth desired max scope depth of the match relative to thescope of the starting position
 * @return first match at a desired relative scope depth,null if there isn't one
 */
static protected MatchResult findInScope(Pattern pattern,CharSequence code,int start,int stop,int minTargetScopeDepth,int maxTargetScopeDepth){
  if (minTargetScopeDepth > maxTargetScopeDepth) {
    int temp=minTargetScopeDepth;
    minTargetScopeDepth=maxTargetScopeDepth;
    maxTargetScopeDepth=temp;
  }
  Matcher m=pattern.matcher(code);
  m.region(start,stop);
  int depth=0;
  int position=start;
  int minScopeDepth=PApplet.min(depth,minTargetScopeDepth);
  while (m.find()) {
    int newPosition=m.end();
    int depthDiff=scopeDepthDiff(code,position,newPosition);
    if (depthDiff != Integer.MAX_VALUE) {
      depth+=depthDiff;
      if (depth < minScopeDepth)       break;
      if (depth >= minTargetScopeDepth && depth <= maxTargetScopeDepth) {
        return m.toMatchResult();
      }
      position=newPosition;
    }
  }
  return null;
}
