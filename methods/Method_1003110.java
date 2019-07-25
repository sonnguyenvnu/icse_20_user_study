private static boolean found(String sql,int start,String other){
  return sql.regionMatches(true,start,other,0,other.length());
}
