@Override public Iterable<Problem> check(SModule m){
{
    SearchScope scope_o7ozeo_a0e=CommandUtil.createScope(m);
    final SearchScope scope_o7ozeo_a0e_0=new EditableFilteringScope(scope_o7ozeo_a0e);
    QueryExecutionContext context=new QueryExecutionContext(){
      public SearchScope getDefaultSearchScope(){
        return scope_o7ozeo_a0e_0;
      }
    }
;
    return CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x57d533a7af16ff67L,"jetbrains.mps.baseLanguage.structure.StatementCommentPart"),false)).select(new ISelector<SNode,Problem>(){
      public Problem select(      SNode it){
        return ((Problem)new DeprecatedConceptNotMigratedProblem(it));
      }
    }
);
  }
}
