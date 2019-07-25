public String print(boolean detailed){
  String name=getName();
  String retval=name != null ? name : NameCache.get(this);
  return retval + ":" + getSite() + (detailed ? printOthers() : "");
}
