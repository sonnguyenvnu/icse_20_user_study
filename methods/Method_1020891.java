public void parse(String joins){
  StringTokenizer tokens=new StringTokenizer(joins,HQL_JOIN_SEPARATORS,true);
  while (tokens.hasMoreElements()) {
    joinClauses.add(this.token(tokens.nextToken()));
  }
}
