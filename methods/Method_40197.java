public String print(){
  StringBuilder sb=new StringBuilder();
  for (  Map.Entry<String,Object> e : contents.entrySet()) {
    sb.append("\n- " + e.getKey() + ": " + e.getValue());
  }
  return sb.toString();
}
