static public String scrubCommentsAndStrings(String p){
  StringBuilder sb=new StringBuilder(p);
  scrubCommentsAndStrings(sb);
  return sb.toString();
}
