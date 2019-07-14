private Set<MethodNameDeclaration> findUnique(Map<MethodNameDeclaration,List<NameOccurrence>> methods){
  Set<MethodNameDeclaration> unique=new HashSet<>();
  Set<String> sigs=new HashSet<>();
  for (  MethodNameDeclaration mnd : methods.keySet()) {
    String sig=mnd.getImage() + mnd.getParameterCount() + mnd.isVarargs();
    if (!sigs.contains(sig)) {
      unique.add(mnd);
    }
    sigs.add(sig);
  }
  return unique;
}
