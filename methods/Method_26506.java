static boolean hasNativeMethods(CompilationUnitTree tree){
  AtomicBoolean hasAnyNativeMethods=new AtomicBoolean(false);
  new TreeScanner<Void,Void>(){
    @Override public Void visitMethod(    MethodTree tree,    Void unused){
      if (tree.getModifiers().getFlags().contains(Modifier.NATIVE)) {
        hasAnyNativeMethods.set(true);
      }
      return null;
    }
  }
.scan(tree,null);
  return hasAnyNativeMethods.get();
}
