public String getArgList(){
  List<String> argList=new ArrayList<>();
  if (args != null) {
    for (    Node n : args) {
      argList.add(n.toDisplay());
    }
  }
  if (vararg != null) {
    argList.add("*" + vararg.toDisplay());
  }
  if (afterRest != null) {
    for (    Node a : afterRest) {
      argList.add(a.toDisplay());
    }
  }
  if (kwarg != null) {
    argList.add("**" + kwarg.toDisplay());
  }
  if (blockarg != null) {
    argList.add("&" + blockarg.toDisplay());
  }
  return _.joinWithSep(argList,",",null,null);
}
