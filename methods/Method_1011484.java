@Override public Iterable<Problem> check(SModule m){
{
    SearchScope scope_vs95qi_a0e=CommandUtil.createScope(m);
    final SearchScope scope_vs95qi_a0e_0=new EditableFilteringScope(scope_vs95qi_a0e);
    QueryExecutionContext context=new QueryExecutionContext(){
      public SearchScope getDefaultSearchScope(){
        return scope_vs95qi_a0e_0;
      }
    }
;
    return CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x110b8590897L,"jetbrains.mps.lang.smodel.structure.Model_NodesOperation"),false)).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return !(SNodeOperations.is(SNodeOperations.getContainingRoot(it),new SNodePointer("r:18ddb7a1-bae8-47e8-a653-f672ff99522d(jetbrains.mps.lang.smodel.migration)","3164834233148532313")));
      }
    }
).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return (SLinkOperations.getTarget(it,MetaAdapterFactory.getReferenceLink(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x110b8590897L,0x110b8590898L,"concept")) != null);
      }
    }
).select(new ISelector<SNode,Problem>(){
      public Problem select(      SNode it){
        return DeprecatedConceptMemberNotMigratedProblem.deprecatedReferenceLink(it,MetaAdapterFactory.getReferenceLink(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x110b8590897L,0x110b8590898L,"concept"));
      }
    }
);
  }
}
