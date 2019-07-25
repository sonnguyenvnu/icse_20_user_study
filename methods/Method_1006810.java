@Override public String[] parse(Criteria criteria){
  StringBuilder sb=new StringBuilder();
  env(criteria);
  resultKey(criteria);
  select(sb,criteria);
  sourceScript(sb,criteria);
  x(sb,criteria);
  groupBy(sb,criteria);
  sort(sb,criteria);
  return sqlArr(sb,criteria);
}
