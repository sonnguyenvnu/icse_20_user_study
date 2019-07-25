@Override public Iterable<Problem> check(SModule m){
{
    SearchScope scope_4t4wzo_a0e=CommandUtil.createScope(m);
    final SearchScope scope_4t4wzo_a0e_0=new EditableFilteringScope(scope_4t4wzo_a0e);
    QueryExecutionContext context=new QueryExecutionContext(){
      public SearchScope getDefaultSearchScope(){
        return scope_4t4wzo_a0e_0;
      }
    }
;
    return CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x3e6a40ba27dd70f3L,"jetbrains.mps.lang.smodel.structure.LanguageRefExpression"),false)).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return SNodeOperations.isInstanceOf(SLinkOperations.getTarget(it,MetaAdapterFactory.getContainmentLink(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x3e6a40ba27dd70f3L,0x312abca18ab995e2L,"languageId")),MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x2246d35517e858c2L,"jetbrains.mps.lang.smodel.structure.LanguageIdentityBySourceModule"));
      }
    }
).select(new ISelector<SNode,DeprecatedConceptNotMigratedProblem>(){
      public DeprecatedConceptNotMigratedProblem select(      SNode it){
        return new DeprecatedConceptNotMigratedProblem(SLinkOperations.getTarget(SNodeOperations.cast(SLinkOperations.getTarget(it,MetaAdapterFactory.getContainmentLink(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x3e6a40ba27dd70f3L,0x312abca18ab995e2L,"languageId")),MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x2246d35517e858c2L,"jetbrains.mps.lang.smodel.structure.LanguageIdentityBySourceModule")),MetaAdapterFactory.getContainmentLink(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x2246d35517e858c2L,0x2246d35517e858e9L,"moduleReference")));
      }
    }
);
  }
}
