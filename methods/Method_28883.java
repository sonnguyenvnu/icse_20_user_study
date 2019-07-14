private String[] getKeys(String key,String... args){
  List<String> list=new ArrayList<String>();
  list.add(key);
  if (args != null && args.length > 0) {
    for (    String arg : args) {
      list.add(arg);
    }
    return list.toArray(new String[0]);
  }
 else {
    return new String[]{key};
  }
}
