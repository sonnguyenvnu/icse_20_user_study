public SourceCode sourceCodeFor(File file){
  return new SourceCode(new SourceCode.FileCodeLoader(file,getSourceEncoding().name()));
}
