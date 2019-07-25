public CompilationResult compile(final List<StringWrappingJavaFile> javaFiles){
  DiagnosticsHolder holder=new DiagnosticsHolder();
  CompilationTask task=compiler.getTask(null,null,holder,options,null,javaFiles);
  if (task.call()) {
    return new CompilationResult(new URLClassLoader(compilationDirectoryUrls),holder.diagnostics);
  }
 else {
    return new CompilationResult(holder.diagnostics);
  }
}
