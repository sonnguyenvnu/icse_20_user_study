protected static List<SimpleName> findAllOccurrences(ASTNode root,String bindingKey){
  List<SimpleName> occurences=new ArrayList<>();
  root.getRoot().accept(new ASTVisitor(){
    @Override public boolean visit(    SimpleName name){
      IBinding binding=resolveBinding(name);
      if (binding != null && bindingKey.equals(binding.getKey())) {
        occurences.add(name);
      }
      return super.visit(name);
    }
  }
);
  return occurences;
}
