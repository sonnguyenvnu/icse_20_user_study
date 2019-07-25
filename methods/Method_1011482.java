@Override public Iterable<Problem> check(SModule m){
{
    SearchScope scope_3qm329_a0e=CommandUtil.createScope(m);
    final SearchScope scope_3qm329_a0e_0=new EditableFilteringScope(scope_3qm329_a0e);
    QueryExecutionContext context=new QueryExecutionContext(){
      public SearchScope getDefaultSearchScope(){
        return scope_3qm329_a0e_0;
      }
    }
;
    return CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x1097542784fL,"jetbrains.mps.lang.smodel.structure.Node_DeleteOperation"),true)).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return SNodeOperations.getPointer(SNodeOperations.getContainingRoot(it)) != new SNodePointer("r:18ddb7a1-bae8-47e8-a653-f672ff99522d(jetbrains.mps.lang.smodel.migration)","7007208114699959039");
      }
    }
).select(new ISelector<SNode,DeprecatedConceptNotMigratedProblem>(){
      public DeprecatedConceptNotMigratedProblem select(      SNode it){
        return new DeprecatedConceptNotMigratedProblem(it);
      }
    }
);
  }
}
