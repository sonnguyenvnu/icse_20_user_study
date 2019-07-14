/** 
 * Iterates result set, maps rows to classes and populates resulting array list.
 * @param types mapping types
 * @param max max number of rows to collect, <code>-1</code> for all
 * @param close <code>true</code> if query is closed at the end, otherwise <code>false</code>.
 * @return list of mapped entities or array of entities
 */
@SuppressWarnings({"unchecked"}) protected <T>List<T> list(Class[] types,final int max,final boolean close){
  List<T> result=new ArrayList<>(initialCollectionSize(max));
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
