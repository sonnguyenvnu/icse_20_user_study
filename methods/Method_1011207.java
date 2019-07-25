@Override public Iterable<Problem> check(SModule m){
  final List<Problem> problems=ListSequence.fromList(new ArrayList<Problem>());
{
    SearchScope scope_lwl7bs_b0e=CommandUtil.createScope(m);
    final SearchScope scope_lwl7bs_b0e_0=new EditableFilteringScope(scope_lwl7bs_b0e);
    QueryExecutionContext context=new QueryExecutionContext(){
      public SearchScope getDefaultSearchScope(){
        return scope_lwl7bs_b0e_0;
      }
    }
;
    CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0x3f4bc5f5c6c14a28L,0x8b10c83066ffa4a1L,0x11a7208faaeL,"jetbrains.mps.lang.constraints.structure.ConceptConstraints"),false)).visitAll(new IVisitor<SNode>(){
      public void visit(      SNode node){
        ConstraintsMigrationUtil.findProblems(SLinkOperations.getTarget(node,MetaAdapterFactory.getContainmentLink(0x3f4bc5f5c6c14a28L,0x8b10c83066ffa4a1L,0x11a7208faaeL,0x5d05239254eb05daL,"canBeChild")),problems);
        ConstraintsMigrationUtil.findProblems(SLinkOperations.getTarget(node,MetaAdapterFactory.getContainmentLink(0x3f4bc5f5c6c14a28L,0x8b10c83066ffa4a1L,0x11a7208faaeL,0x5d05239254e7e6a8L,"canBeParent")),problems);
        ConstraintsMigrationUtil.findProblems(SLinkOperations.getTarget(node,MetaAdapterFactory.getContainmentLink(0x3f4bc5f5c6c14a28L,0x8b10c83066ffa4a1L,0x11a7208faaeL,0x5d05239254e7e6baL,"canBeAncestor")),problems);
      }
    }
);
  }
  return problems;
}
