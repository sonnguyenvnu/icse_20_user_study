public static <O>Predicate<O> disallowEmpty(Class<O> clazz){
  return o -> {
    if (o == null) {
      return false;
    }
    if (o instanceof String) {
      return StringUtils.isNotBlank((String)o);
    }
    return (!o.getClass().isArray() || (Array.getLength(o) != 0 && Array.get(o,0) != null)) && (!(o instanceof Collection) || (!((Collection)o).isEmpty() && ((Collection)o).iterator().next() != null));
  }
;
}
