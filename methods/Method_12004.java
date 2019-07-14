/** 
 * Evaluates whether  {@link FrameworkMethod}s are ignored based on the {@link Ignore} annotation.
 */
@Override protected boolean isIgnored(FrameworkMethod child){
  return child.getAnnotation(Ignore.class) != null;
}
