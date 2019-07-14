public synchronized Map<String,String> getImportedClasses(String name) throws ClassNotFoundException {
  if (dontBother.containsKey(name)) {
    throw new ClassNotFoundException(name);
  }
  try (InputStream classResource=getResourceAsStream(name.replace('.','/') + ".class")){
    ClassReader reader=new ClassReader(classResource);
    PMDASMVisitor asmVisitor=new PMDASMVisitor(name);
    reader.accept(asmVisitor,0);
    List<String> inner=asmVisitor.getInnerClasses();
    if (inner != null && !inner.isEmpty()) {
      inner=new ArrayList<>(inner);
      for (      String str : inner) {
        try (InputStream innerClassStream=getResourceAsStream(str.replace('.','/') + ".class")){
          if (innerClassStream != null) {
            reader=new ClassReader(innerClassStream);
            reader.accept(asmVisitor,0);
          }
        }
       }
    }
    return asmVisitor.getPackages();
  }
 catch (  IOException e) {
    dontBother.put(name,Boolean.TRUE);
    throw new ClassNotFoundException(name,e);
  }
}
