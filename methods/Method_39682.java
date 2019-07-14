/** 
 * Returns name of method reference. Target  {@link #of(Class) method} hasto be  {@link #get() called} before it can return its reference.
 */
public String ref(){
  if (instance == null) {
    return null;
  }
  try {
    final Field f=instance.getClass().getDeclaredField("$__methodName$0");
    f.setAccessible(true);
    final Object name=f.get(instance);
    if (name == null) {
      throw new MethrefException("Target method not collected");
    }
    return name.toString();
  }
 catch (  final Exception ex) {
    if (ex instanceof MethrefException) {
      throw ((MethrefException)ex);
    }
    throw new MethrefException("Methref field not found",ex);
  }
}
