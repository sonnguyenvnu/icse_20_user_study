public void initializeWith(ClassLoader classLoader,ASTCompilationUnit node){
  ClassTypeResolver classTypeResolver=new ClassTypeResolver(classLoader);
  node.setClassTypeResolver(classTypeResolver);
  node.jjtAccept(classTypeResolver,null);
}
