@Override public Iterable<Problem> check(SModule m){
{
    SearchScope scope_teljlc_a0e=CommandUtil.createScope(m);
    final SearchScope scope_teljlc_a0e_0=new EditableFilteringScope(scope_teljlc_a0e);
    QueryExecutionContext context=new QueryExecutionContext(){
      public SearchScope getDefaultSearchScope(){
        return scope_teljlc_a0e_0;
      }
    }
;
    return CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x527e98a73771f42dL,"jetbrains.mps.lang.smodel.structure.ConceptSwitchStatement"),false)).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return !(TypecheckingFacade.getFromContext().isSubtype(TypecheckingFacade.getFromContext().getTypeOf(SLinkOperations.getTarget(it,MetaAdapterFactory.getContainmentLink(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x527e98a73771f42dL,0x527e98a73771f430L,"expression"))),_quotation_createNode_teljlc_b0a0a0a0a0a0a0a7())) && !(TypecheckingFacade.getFromContext().isSubtype(TypecheckingFacade.getFromContext().getTypeOf(SLinkOperations.getTarget(it,MetaAdapterFactory.getContainmentLink(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x527e98a73771f42dL,0x527e98a73771f430L,"expression"))),_quotation_createNode_teljlc_b0a0a0a0a0a0a0a7_0()));
      }
    }
).select(new ISelector<SNode,Problem>(){
      public Problem select(      SNode it){
        return ((Problem)new NotMigratedNode(it){
          public String getMessage(){
            return "Concept switch with type other than concept<>";
          }
        }
);
      }
    }
);
  }
}
