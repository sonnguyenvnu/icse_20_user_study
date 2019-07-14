public String printByTo(){
  StringBuffer sb=new StringBuffer();
  sb.append("========?????========\n");
  for (int to=0; to < edgesTo.length; ++to) {
    List<EdgeFrom> edgeFromList=edgesTo[to];
    for (    EdgeFrom edgeFrom : edgeFromList) {
      sb.append(String.format("to:%3d, from:%3d, weight:%05.2f, word:%s\n",to,edgeFrom.from,edgeFrom.weight,edgeFrom.name));
    }
  }
  return sb.toString();
}
