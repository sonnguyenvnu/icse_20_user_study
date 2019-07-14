private void compileModuleInfo(String module,String moduleInfo,String modulePath){
class MemoryJavaFileObject extends SimpleJavaFileObject {
    MemoryJavaFileObject(    String name,    String code){
      super(Paths.get(name).toUri(),Kind.SOURCE);
      this.code=code;
    }
    @Override public CharSequence getCharContent(    boolean ignoreEncodingErrors){
      return code;
    }
  }
  List<JavaFileObject> compilationUnits=new ArrayList<>(32);
  compilationUnits.add(new MemoryJavaFileObject("modules/lwjgl/" + module + "/src/main/java/module-info.java",moduleInfo));
  JavaCompiler.CompilationTask task=compiler.getTask(null,fileManager,diagnostics,Arrays.asList("--module-path",modulePath,"-d","bin/classes/lwjgl/" + module),null,compilationUnits);
  boolean success=task.call();
  System.out.println(module + ": " + (success ? "OK" : "FAILED"));
  if (!success) {
    System.out.flush();
    diagnostics.getDiagnostics().forEach(it -> {
      System.err.println(it.getCode());
      System.err.println(it.getKind());
      System.err.println(it.getPosition());
      System.err.println(it.getStartPosition());
      System.err.println(it.getEndPosition());
      System.err.println(it.getSource());
      System.err.println(it.getMessage(null));
    }
);
    System.exit(1);
  }
}
