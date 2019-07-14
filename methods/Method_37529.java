/** 
 * Scans  {@link jodd.util.ClassLoaderUtil#getDefaultClasspath() default class path}.
 */
public ClassScanner scanDefaultClasspath(){
  return scan(ClassLoaderUtil.getDefaultClasspath());
}
