public ApexCompiler visitAstsFromStrings(List<String> sources,AstVisitor<AdditionalPassScope> visitor){
  return visitAstsFromStrings(sources,visitor,CompilerStage.POST_TYPE_RESOLVE);
}
