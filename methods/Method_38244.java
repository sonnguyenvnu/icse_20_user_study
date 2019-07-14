@SuppressWarnings({"unchecked"}) protected <T>Set<T> listSet(Class[] types,final int max,final boolean close){
  final Set<T> result=new LinkedHashSet<>(initialCollectionSize(max));
  ResultSetMapper rsm=executeAndBuildResultSetMapper();
  if (types == null) {
    types=rsm.resolveTables();
  }
  Object previousElement=null;
  while (rsm.next()) {
    Object[] objects=rsm.parseObjects(types);
    Object row=resolveRowResults(objects);
    int size=result.size();
    T newElement=(T)row;
    if (entityAwareMode && size > 0) {
      if (previousElement != null && newElement != null) {
        boolean equals;
        if (newElement.getClass().isArray()) {
          equals=Arrays.equals((Object[])previousElement,(Object[])newElement);
        }
 else {
          equals=previousElement.equals(newElement);
        }
        if (equals) {
          continue;
        }
      }
    }
    if (size == max) {
      break;
    }
    result.add(newElement);
    previousElement=newElement;
  }
  close(rsm,close);
  return result;
}
