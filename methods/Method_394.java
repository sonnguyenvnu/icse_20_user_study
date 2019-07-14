@Override public Object clone(){
  return new JSONArray(new ArrayList<Object>(list));
}
