/** 
 * Builder method that will create and return an instance that has specified {@link Visibility} value to use for all property elements.Typical usage would be something like: <pre> mapper.setVisibilityChecker( mapper.getVisibilityChecker().with(Visibility.NONE)); </pre> (which would basically disable all auto-detection)
 */
public VisibilityChecker with(Visibility v){
  if (v == Visibility.DEFAULT) {
    return DEFAULT;
  }
  return new VisibilityChecker(v);
}
