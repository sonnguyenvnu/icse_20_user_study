/** 
 * Creates (or returns) the path to a dex file which defines the superclass of  {@clz} as extending{@code realSuperClz}, which by itself must extend  {@code topClz}.
 */
public static File ensure(String clz,Class<?> realSuperClz,Class<?> topClz) throws IOException {
  if (!topClz.isAssignableFrom(realSuperClz)) {
    throw new ClassCastException("Cannot initialize " + clz + " because " + realSuperClz + " does not extend " + topClz);
  }
  try {
    return ensure("xposed.dummy." + clz + "SuperClass",realSuperClz);
  }
 catch (  IOException e) {
    throw new IOException("Failed to create a superclass for " + clz,e);
  }
}
