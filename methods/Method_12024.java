/** 
 * Adds to  {@code errors} if this method:<ul> <li>is not public, or <li>takes parameters, or <li>returns something other than void, or <li>is static (given  {@code isStatic is false}), or <li>is not static (given  {@code isStatic is true}). </ul>
 */
public void validatePublicVoidNoArg(boolean isStatic,List<Throwable> errors){
  validatePublicVoid(isStatic,errors);
  if (method.getParameterTypes().length != 0) {
    errors.add(new Exception("Method " + method.getName() + " should have no parameters"));
  }
}
