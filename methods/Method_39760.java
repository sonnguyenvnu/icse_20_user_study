/** 
 * Checks for all public super methods that are not overridden.
 */
protected void processSuperMethods(){
  for (  ClassReader cr : targetClassInfo.superClassReaders) {
    cr.accept(new EmptyClassVisitor(){
      @Override public void visit(      final int version,      final int access,      final String name,      final String signature,      final String superName,      final String[] interfaces){
        declaredClassName=name;
      }
      @Override public MethodVisitor visitMethod(      final int access,      final String name,      final String desc,      final String signature,      final String[] exceptions){
        if (name.equals(INIT) || name.equals(CLINIT)) {
          return null;
        }
        MethodSignatureVisitor msign=targetClassInfo.lookupMethodSignatureVisitor(access,name,desc,declaredClassName);
        if (msign == null) {
          return null;
        }
        return applyProxy(msign);
      }
    }
,0);
  }
}
