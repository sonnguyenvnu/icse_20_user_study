/** 
 * Compares two  {@link MethodParamModel}s based on name and annotations only. 
 */
public static Comparator<MethodParamModel> shallowParamComparator(){
  return Comparator.nullsLast(Comparator.comparing(MethodParamModel::getName).thenComparing((a,b) -> (a.getTypeName() == null && a.getTypeName() == b.getTypeName()) || a.getTypeName().equals(b.getTypeName()) ? 0 : -1).thenComparing((a,b) -> a.getAnnotations().equals(b.getAnnotations()) ? 0 : -1));
}
