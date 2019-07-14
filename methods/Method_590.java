public Collection<String> newCollectionByType(Class<?> type){
  if (type.isAssignableFrom(HashSet.class)) {
    HashSet<String> list=new HashSet<String>();
    return list;
  }
 else   if (type.isAssignableFrom(ArrayList.class)) {
    ArrayList<String> list2=new ArrayList<String>();
    return list2;
  }
 else {
    try {
      Collection<String> list=(Collection<String>)type.newInstance();
      return list;
    }
 catch (    Exception e) {
      throw new JSONException(e.getMessage(),e);
    }
  }
}
