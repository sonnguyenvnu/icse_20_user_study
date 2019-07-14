private String pretty(JCVariableDecl variableDecl){
  StringWriter sw=new StringWriter();
  try {
    new Pretty(sw,true){
      @Override public void visitAnnotation(      JCAnnotation anno){
        if (anno.getArguments().isEmpty()) {
          try {
            print("@");
            printExpr(anno.annotationType);
          }
 catch (          IOException e) {
            throw new UncheckedIOException(e);
          }
        }
 else {
          super.visitAnnotation(anno);
        }
      }
    }
.printStat(variableDecl);
  }
 catch (  IOException e) {
    throw new UncheckedIOException(e);
  }
  return sw.toString();
}
