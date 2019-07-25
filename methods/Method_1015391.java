protected static String explanation(boolean connection_existed,boolean replace){
  StringBuilder sb=new StringBuilder();
  if (connection_existed) {
    sb.append(" (connection existed");
    if (replace)     sb.append(" but was replaced because my address is lower)");
 else     sb.append(" and my address won as it's higher)");
  }
 else   sb.append(" (connection didn't exist)");
  return sb.toString();
}
