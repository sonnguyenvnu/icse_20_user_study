public String[] keys(){
  _check_inited();
  Set<String> keys=sqls.keySet();
  return keys.toArray(new String[keys.size()]);
}
