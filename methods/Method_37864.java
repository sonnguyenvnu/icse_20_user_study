/** 
 * Returns location of the class. If class is not in a jar, it's classpath is returned; otherwise the jar location.
 */
public static String classLocation(final Class clazz){
  return clazz.getProtectionDomain().getCodeSource().getLocation().getPath();
}
