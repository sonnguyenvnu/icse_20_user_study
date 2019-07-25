public Display replace(String src,String dest){
  final List<CharSequence> newDisplay=new ArrayList<CharSequence>();
  for (  CharSequence cs : displayData) {
    if (cs.toString().contains(src)) {
      cs=cs.toString().replace(src,dest);
    }
    newDisplay.add(cs);
  }
  return new Display(newDisplay,naturalHorizontalAlignment,isNull,defaultCreoleMode);
}
