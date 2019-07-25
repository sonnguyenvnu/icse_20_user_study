public String print(){
  StringBuilder sb=new StringBuilder();
  for (  PingData data : this)   sb.append(data).append("\n");
  return sb.toString();
}
