@NotNull @Override public String toDisplay(){
  StringBuilder sb=new StringBuilder();
  sb.append("(");
  int idx=0;
  for (  Node n : elts) {
    if (idx != 0) {
      sb.append(", ");
    }
    idx++;
    sb.append(n.toDisplay());
  }
  sb.append(")");
  return sb.toString();
}
