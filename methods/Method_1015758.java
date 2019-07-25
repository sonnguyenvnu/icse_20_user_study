public static String bold(String msg){
  StringBuilder sb=new StringBuilder("\033[1m");
  sb.append(msg).append("\033[0m");
  return sb.toString();
}
