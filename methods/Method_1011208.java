@Override public Iterable<Problem> check(SModule m){
  final List<Problem> problems=ListSequence.fromList(new ArrayList<Problem>());
{
    SearchScope scope_djohgv_b0e=CommandUtil.createScope(m);
    final SearchScope scope_djohgv_b0e_0=new EditableFilteringScope(scope_djohgv_b0e);
    QueryExecutionContext context=new QueryExecutionContext(){
      public SearchScope getDefaultSearchScope(){
        return scope_djohgv_b0e_0;
      }
    }
;
    CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0x3f4bc5f5c6c14a28L,0x8b10c83066ffa4a1L,0x11a7208faaeL,"jetbrains.mps.lang.constraints.structure.ConceptConstraints"),false)).visitAll(new IVisitor<SNode>(){
      public void visit(      SNode node){
        ConstraintsMigrationUtil.findProblems(SLinkOperations.getTarget(SLinkOperations.getTarget(node,MetaAdapterFactory.getContainmentLink(0x3f4bc5f5c6c14a28L,0x8b10c83066ffa4a1L,0x11a7208faaeL,0x11a727527f6L,"defaultScope")),MetaAdapterFactory.getContainmentLink(0x3f4bc5f5c6c14a28L,0x8b10c83066ffa4a1L,0x10dead47852L,0x10dead647b3L,"searchScopeFactory")),problems);
        ListSequence.fromList(SLinkOperations.getChildren(node,MetaAdapterFactory.getContainmentLink(0x3f4bc5f5c6c14a28L,0x8b10c83066ffa4a1L,0x11a7208faaeL,0x11a726c901bL,"referent"))).visitAll(new IVisitor<SNode>(){
          public void visit(          SNode referent){
            ConstraintsMigrationUtil.findProblems(SLinkOperations.getTarget(referent,MetaAdapterFactory.getContainmentLink(0x3f4bc5f5c6c14a28L,0x8b10c83066ffa4a1L,0x10b731752daL,0x10b7319e797L,"searchScopeFactory")),problems);
          }
        }
);
      }
    }
);
  }
  return problems;
}
