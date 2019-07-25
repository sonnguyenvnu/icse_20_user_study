@Override public Iterable<Problem> check(SModule m){
{
    SearchScope scope_32rjqx_a0e=CommandUtil.createScope(m);
    final SearchScope scope_32rjqx_a0e_0=new EditableFilteringScope(scope_32rjqx_a0e);
    QueryExecutionContext context=new QueryExecutionContext(){
      public SearchScope getDefaultSearchScope(){
        return scope_32rjqx_a0e_0;
      }
    }
;
    return CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0xb401a68083254110L,0x8fd384331ff25befL,0xfd47e9f6f0L,"jetbrains.mps.lang.generator.structure.PropertyMacro"),false)).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return SNodeOperations.isInstanceOf(SNodeOperations.getParent(PropertyAttribute__BehaviorDescriptor.getPropertyDeclaration_id121FNPYBLc9.invoke(it)),MetaAdapterFactory.getConcept(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0x5a14f1035942a5abL,"jetbrains.mps.lang.structure.structure.EnumPropertyMigrationInfo"));
      }
    }
).select(new ISelector<SNode,UsageOfMigrateNodeNotMigratedProblem>(){
      public UsageOfMigrateNodeNotMigratedProblem select(      SNode it){
        return new UsageOfMigrateNodeNotMigratedProblem(it,PropertyAttribute__BehaviorDescriptor.getPropertyDeclaration_id121FNPYBLc9.invoke(it));
      }
    }
);
  }
}
