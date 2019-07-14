private ApexCompiler compile(CompilationInput compilationInput,CompilerStage compilerStage){
  ApexCompiler compiler=ApexCompiler.builder().setInput(compilationInput).build();
  compiler.compile(compilerStage);
  callAdditionalPassVisitor(compiler);
  return compiler;
}
