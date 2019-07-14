public static List<Edit> preprocessAST(CompilationUnit cu){
  final List<Edit> edits=new ArrayList<>();
  cu.accept(new ASTVisitor(){
    @Override public boolean visit(    SimpleType node){
      if ("color".equals(node.getName().toString())) {
        edits.add(Edit.replace(node.getStartPosition(),node.getLength(),"int"));
      }
      return super.visit(node);
    }
    @Override public boolean visit(    NumberLiteral node){
      String s=node.getToken().toLowerCase();
      if (FLOATING_POINT_LITERAL_VERIFIER.matcher(s).matches() && !s.endsWith("f") && !s.endsWith("d")) {
        edits.add(Edit.insert(node.getStartPosition() + node.getLength(),"f"));
      }
      return super.visit(node);
    }
    @Override public boolean visit(    MethodDeclaration node){
      int accessModifiers=node.getModifiers() & ACCESS_MODIFIERS_MASK;
      if (accessModifiers == 0) {
        edits.add(Edit.insert(node.getStartPosition(),"public "));
      }
      return super.visit(node);
    }
  }
);
  return edits;
}
