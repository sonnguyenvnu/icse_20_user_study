/** 
 * Adds to  {@code errors} if this method:<ul> <li>is not public, or <li>returns something other than void, or <li>is static (given  {@code isStatic is false}), or <li>is not static (given  {@code isStatic is true}). </ul>
 */
public void validatePublicVoid(boolean isStatic,List<Throwable> errors){
  if (isStatic() != isStatic) {
    String state=isStatic ? "should" : "should not";
    errors.add(new Exception("Method " + method.getName() + "() " + state + " be static"));
  }
  if (!isPublic()) {
    errors.add(new Exception("Method " + method.getName() + "() should be public"));
  }
  if (method.getReturnType() != Void.TYPE) {
    errors.add(new Exception("Method " + method.getName() + "() should be void"));
  }
}
