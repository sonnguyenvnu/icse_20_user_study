private <T>Set<T> toSet(T... types){
  if (types == null || types.length == 0)   return Sets.newHashSet();
  return Sets.newHashSet(types);
}
