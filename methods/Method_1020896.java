public void parse(String wheres){
  StringTokenizer tokens=new StringTokenizer(wheres,HQL_SEPARATORS,true);
  while (tokens.hasMoreElements()) {
    String token=tokens.nextToken();
    whereConditions.add(this.token(token));
  }
}
