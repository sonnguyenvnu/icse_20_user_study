public void index(String name){
  try {
    String className=getClassName(name);
    String classResourceName=getResourceName(name);
    if (classFilter.filter(className)) {
      if (!usageGraph.isClass(className)) {
        usageGraph.defineClass(className);
        try (InputStream inputStream=this.getClass().getClassLoader().getResourceAsStream(classResourceName + ".class")){
          ClassReader classReader=new ClassReader(inputStream);
          classReader.accept(getNewClassVisitor(),0);
        }
       }
    }
  }
 catch (  IOException e) {
    throw new RuntimeException("For " + name + ": " + e.getMessage(),e);
  }
}
