public String applyRule(String str,int cur,TaggerImpl tagger){
  StringBuilder sb=new StringBuilder();
  for (  String tmp : str.split("%x",-1)) {
    if (tmp.startsWith("U") || tmp.startsWith("B")) {
      sb.append(tmp);
    }
 else     if (tmp.length() > 0) {
      String[] tuple=tmp.split("]");
      String[] idx=tuple[0].replace("[","").split(",");
      String r=getIndex(idx,cur,tagger);
      if (r != null) {
        sb.append(r);
      }
      if (tuple.length > 1) {
        sb.append(tuple[1]);
      }
    }
  }
  return sb.toString();
}
