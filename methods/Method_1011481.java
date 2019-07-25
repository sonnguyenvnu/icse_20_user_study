@Override public Iterable<Problem> check(SModule m){
{
    SearchScope scope_5d7h7i_a0e=CommandUtil.createScope(m);
    final SearchScope scope_5d7h7i_a0e_0=new EditableFilteringScope(scope_5d7h7i_a0e);
    QueryExecutionContext context=new QueryExecutionContext(){
      public SearchScope getDefaultSearchScope(){
        return scope_5d7h7i_a0e_0;
      }
    }
;
    return CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x195fd0576ac9bb49L,"jetbrains.mps.lang.smodel.structure.NodePointerExpression_Old"),false)).select(new ISelector<SNode,DeprecatedConceptNotMigratedProblem>(){
      public DeprecatedConceptNotMigratedProblem select(      SNode it){
        return new DeprecatedConceptNotMigratedProblem(it);
      }
    }
);
  }
}
