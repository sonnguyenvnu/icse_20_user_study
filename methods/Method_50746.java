private List<ASTMethod> findConstructorlMethods(){
  final ArrayList<ASTMethod> ret=new ArrayList<>();
  final Set<String> constructors=classMethods.keySet().stream().filter(p -> p.contains("<init>") || p.contains("<clinit>") || p.startsWith(className + ":" + className + ":")).collect(Collectors.toSet());
  for (  String c : constructors) {
    ret.add(classMethods.get(c));
  }
  return ret;
}
