private void resolveType(ASTCompilationUnit node,Object data){
  FixClassTypeResolver classTypeResolver=new FixClassTypeResolver(AbstractAliRule.class.getClassLoader());
  node.setClassTypeResolver(classTypeResolver);
  node.jjtAccept(classTypeResolver,data);
}
