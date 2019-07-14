@NotNull List<Name> segmentQname(@NotNull String qname,int start,boolean hasLoc){
  List<Name> result=new ArrayList<>();
  for (int i=0; i < qname.length(); i++) {
    String name="";
    while (Character.isSpaceChar(qname.charAt(i))) {
      i++;
    }
    int nameStart=i;
    while (i < qname.length() && (Character.isJavaIdentifierPart(qname.charAt(i)) || qname.charAt(i) == '*') && qname.charAt(i) != '.') {
      name+=qname.charAt(i);
      i++;
    }
    int nameStop=i;
    int nstart=hasLoc ? start + nameStart : -1;
    int nstop=hasLoc ? start + nameStop : -1;
    result.add(new Name(name,file,nstart,nstop));
  }
  return result;
}
