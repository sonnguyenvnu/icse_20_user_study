protected <T>String glomNames(Set<T> s){
  StringBuilder result=new StringBuilder();
  for (  T t : s) {
    result.append(t.toString());
    result.append(',');
  }
  return result.length() == 0 ? "" : result.toString().substring(0,result.length() - 1);
}
