private List<String> keylist(){
  if (null == _sql_keys)   this.refresh();
  return _sql_keys;
}
