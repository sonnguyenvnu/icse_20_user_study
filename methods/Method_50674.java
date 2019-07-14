private CompilationInput createCompilationInput(List<SourceFile> sourceFiles,AstVisitor<AdditionalPassScope> visitor){
  return new CompilationInput(sourceFiles,symbolProvider,accessEvaluator,queryValidator,visitor,NoopCompilerProgressCallback.get());
}
