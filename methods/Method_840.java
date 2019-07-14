private void populateClassName(ASTCompilationUnit node,String className) throws ClassNotFoundException {
  node.setType(pmdClassLoader.loadClass(className));
  importedClasses.putAll(pmdClassLoader.getImportedClasses(className));
}
