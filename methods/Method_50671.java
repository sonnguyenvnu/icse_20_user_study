public ApexCompiler visitAstFromString(String source,AstVisitor<AdditionalPassScope> visitor){
  return visitAstsFromStrings(ImmutableList.of(source),visitor,CompilerStage.POST_TYPE_RESOLVE);
}
