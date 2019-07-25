public void parse(String wheres){
  StringTokenizer tokens=new StringTokenizer(wheres,HQL_SELECT_SEPARATORS,true);
  while (tokens.hasMoreElements()) {
    String token=tokens.nextToken();
    selectConditions.add(this.token(token));
  }
}
