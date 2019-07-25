public void compile(Collection<String> classPath,@NotNull JavaCompilerOptions customCompilerOptions){
  Map<String,String> compilerOptions=addPresetCompilerOptions(customCompilerOptions);
  CompilerOptions options=new CompilerOptions(compilerOptions);
  Compiler compiler=new Compiler(new JDKFileSystem(classPath,new String[0]),new ProceedingOnErrorsPolicy(),options,new RelayingRequestor(),new DefaultProblemFactory());
  try {
    Collection<CompilationUnit> compilationUnits=myCompilationUnits.values();
    compiler.compile(compilationUnits.toArray(new CompilationUnit[0]));
  }
 catch (  RuntimeException ex) {
    onFatalError(ex.getMessage());
  }
}
