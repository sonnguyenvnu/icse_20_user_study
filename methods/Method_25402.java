/** 
 * Matches a class in which at least one method matches the given methodMatcher.
 * @param methodMatcher A matcher on MethodTrees to run against all methods in this class.
 * @return True if some method in the class matches the given methodMatcher.
 */
public static Matcher<ClassTree> hasMethod(Matcher<MethodTree> methodMatcher){
  return (t,state) -> {
    for (    Tree member : t.getMembers()) {
      if (member instanceof MethodTree && methodMatcher.matches((MethodTree)member,state)) {
        return true;
      }
    }
    return false;
  }
;
}
