public static boolean compare(Object source,Object target){
  if (source == target) {
    return true;
  }
  if (source == null || target == null) {
    return false;
  }
  if (source.equals(target)) {
    return true;
  }
  if (source instanceof Number) {
    return compare(((Number)source),target);
  }
  if (target instanceof Number) {
    return compare(((Number)target),source);
  }
  if (source instanceof Date) {
    return compare(((Date)source),target);
  }
  if (target instanceof Date) {
    return compare(((Date)target),source);
  }
  if (source instanceof String) {
    return compare(((String)source),target);
  }
  if (target instanceof String) {
    return compare(((String)target),source);
  }
  if (source instanceof Collection) {
    return compare(((Collection)source),target);
  }
  if (target instanceof Collection) {
    return compare(((Collection)target),source);
  }
  if (source instanceof Map) {
    return compare(((Map)source),target);
  }
  if (target instanceof Map) {
    return compare(((Map)target),source);
  }
  if (source.getClass().isEnum()) {
    return compare(((Enum)source),target);
  }
  if (target.getClass().isEnum()) {
    return compare(((Enum)target),source);
  }
  if (source.getClass().isArray()) {
    return compare(((Object[])source),target);
  }
  if (target.getClass().isArray()) {
    return compare(((Object[])target),source);
  }
  return compare(FastBeanCopier.copy(source,HashMap.class),FastBeanCopier.copy(target,HashMap.class));
}
