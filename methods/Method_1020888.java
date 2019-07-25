public void parse(String common){
  StringTokenizer tokens=new StringTokenizer(common,HQL_JOIN_SEPARATORS,true);
  while (tokens.hasMoreElements()) {
    String token=tokens.nextToken();
    orderList.add(this.token(token));
  }
}
