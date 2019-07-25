@Override public Iterable<Problem> check(SModule m){
  Iterable<Problem> result;
{
    SearchScope scope_n70cuq_b0e=CommandUtil.createScope(m);
    final SearchScope scope_n70cuq_b0e_0=new EditableFilteringScope(scope_n70cuq_b0e);
    QueryExecutionContext context=new QueryExecutionContext(){
      public SearchScope getDefaultSearchScope(){
        return scope_n70cuq_b0e_0;
      }
    }
;
    result=CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0xf979ba0450L,"jetbrains.mps.lang.structure.structure.ConceptDeclaration"),false)).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return isNotEmptyString(SPropertyOperations.getString(it,MetaAdapterFactory.getProperty(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0xf979ba0450L,0x10e328118ddL,"iconPath")));
      }
    }
).select(new ISelector<SNode,Problem>(){
      public Problem select(      SNode it){
        return ((Problem)new NotMigratedNode(it){
          public String getMessage(){
            return "Icon path was not migrated";
          }
        }
);
      }
    }
);
  }
  return result;
}
