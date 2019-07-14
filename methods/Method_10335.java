protected boolean isInList(HashSet<Class<?>> list,Throwable error){
  for (  Class<?> aList : list) {
    if (aList.isInstance(error)) {
      return true;
    }
  }
  return false;
}
