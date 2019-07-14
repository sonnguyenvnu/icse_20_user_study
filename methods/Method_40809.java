public static String printList(List<? extends Node> nodes){
  StringBuilder sb=new StringBuilder();
  boolean first=true;
  for (  Node e : nodes) {
    if (!first) {
      sb.append(" ");
    }
    sb.append(e);
    first=false;
  }
  return sb.toString();
}
