@Override public Iterable<Problem> check(SModule m){
{
    SearchScope scope_3t318f_a0e=CommandUtil.createScope(m);
    final SearchScope scope_3t318f_a0e_0=new EditableFilteringScope(scope_3t318f_a0e);
    QueryExecutionContext context=new QueryExecutionContext(){
      public SearchScope getDefaultSearchScope(){
        return scope_3t318f_a0e_0;
      }
    }
;
    return CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0xfc26875dfbL,"jetbrains.mps.lang.structure.structure.EnumerationDataTypeDeclaration_Old"),false)).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return !(SNodeOperations.isInstanceOf(SNodeOperations.getParent(it),MetaAdapterFactory.getConcept(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0x160b046db949c266L,"jetbrains.mps.lang.structure.structure.EnumMigrationInfo")));
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
