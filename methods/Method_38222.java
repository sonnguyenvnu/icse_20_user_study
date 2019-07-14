/** 
 * Moves to next element.
 */
private boolean moveToNext(){
  if (last) {
    if (closeOnEnd) {
      query.close();
    }
 else {
      query.closeResultSet(resultSetMapper.getResultSet());
    }
    return false;
  }
  while (true) {
    if (!resultSetMapper.next()) {
      last=true;
      return entityAwareMode;
    }
    Object[] objects=resultSetMapper.parseObjects(types);
    Object row=query.resolveRowResults(objects);
    newElement=(T)row;
    if (entityAwareMode) {
      if (count == 0 && previousElement == null) {
        previousElement=newElement;
        continue;
      }
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
    break;
  }
  return true;
}
