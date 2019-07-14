/** 
 * Reflectively instantiate the package-private  {@code MethodResolutionPhase} enum. 
 */
private static Object newMethodResolutionPhase(boolean autoboxing){
  for (  Class<?> c : Resolve.class.getDeclaredClasses()) {
    if (!c.getName().equals("com.sun.tools.javac.comp.Resolve$MethodResolutionPhase")) {
      continue;
    }
    for (    Object e : c.getEnumConstants()) {
      if (e.toString().equals(autoboxing ? "BOX" : "BASIC")) {
        return e;
      }
    }
  }
  return null;
}
