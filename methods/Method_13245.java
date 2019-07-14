protected static void populateInnerTypePaths(final HashSet<String> innerTypePaths,Container.Entry entry){
  try (InputStream is=entry.getInputStream()){
    ClassReader classReader=new ClassReader(is);
    String p=entry.getPath();
    final String prefixPath=p.substring(0,p.length() - classReader.getClassName().length() - 6);
    ClassVisitor classVisitor=new ClassVisitor(Opcodes.ASM7){
      public void visitInnerClass(      final String name,      final String outerName,      final String innerName,      final int access){
        innerTypePaths.add(prefixPath + name + ".class");
      }
    }
;
    classReader.accept(classVisitor,ClassReader.SKIP_CODE | ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
  }
 catch (  Exception e) {
    assert ExceptionUtil.printStackTrace(e);
  }
}
