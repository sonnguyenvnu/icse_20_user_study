@Override public Iterable<Problem> check(SModule m){
  List<Problem> problems=ListSequence.fromList(new ArrayList<Problem>());
{
    SearchScope scope_lpnriw_b0e=CommandUtil.createScope(m);
    final SearchScope scope_lpnriw_b0e_0=new EditableFilteringScope(scope_lpnriw_b0e);
    QueryExecutionContext context=new QueryExecutionContext(){
      public SearchScope getDefaultSearchScope(){
        return scope_lpnriw_b0e_0;
      }
    }
;
    ListSequence.fromList(problems).addSequence(CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0x3f4bc5f5c6c14a28L,0x8b10c83066ffa4a1L,0x36367902116a44c1L,"jetbrains.mps.lang.constraints.structure.ConstraintFunction_ReferentSearchScope_Presentation"),false)).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return ListSequence.fromList(SLinkOperations.getChildren(AttributeOperations.getAttribute(it,new IAttributeDescriptor.NodeAttribute(MetaAdapterFactory.getConcept(0x3f4bc5f5c6c14a28L,0x8b10c83066ffa4a1L,0x583cd121d513aabeL,"jetbrains.mps.lang.constraints.structure.RefPresentationMigrated"))),MetaAdapterFactory.getContainmentLink(0x3f4bc5f5c6c14a28L,0x8b10c83066ffa4a1L,0x583cd121d513aabeL,0x4fd9d41024c6d474L,"problems"))).isNotEmpty();
      }
    }
).select(new ISelector<SNode,PresentationQueryMigratedWithProblems>(){
      public PresentationQueryMigratedWithProblems select(      SNode it){
        return new PresentationQueryMigratedWithProblems(it);
      }
    }
));
    ListSequence.fromList(problems).addSequence(Sequence.fromIterable(SLinkOperations.collect(SLinkOperations.collect(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0x3f4bc5f5c6c14a28L,0x8b10c83066ffa4a1L,0x11a7208faaeL,"jetbrains.mps.lang.constraints.structure.ConceptConstraints"),false),MetaAdapterFactory.getContainmentLink(0x3f4bc5f5c6c14a28L,0x8b10c83066ffa4a1L,0x11a7208faaeL,0x11a727527f6L,"defaultScope")),MetaAdapterFactory.getContainmentLink(0x3f4bc5f5c6c14a28L,0x8b10c83066ffa4a1L,0x10dead47852L,0x36367902116b5f22L,"presentation"))).select(new ISelector<SNode,DefaultPresentationQueryNotMigrated>(){
      public DefaultPresentationQueryNotMigrated select(      SNode it){
        return new DefaultPresentationQueryNotMigrated(it);
      }
    }
));
    return problems;
  }
}
