@Override public Iterable<Problem> check(SModule m){
{
    SearchScope scope_wz5m8m_a0e=CommandUtil.createScope(m);
    final SearchScope scope_wz5m8m_a0e_0=new EditableFilteringScope(scope_wz5m8m_a0e);
    QueryExecutionContext context=new QueryExecutionContext(){
      public SearchScope getDefaultSearchScope(){
        return scope_wz5m8m_a0e_0;
      }
    }
;
    List<Problem> result=ListSequence.fromList(new ArrayList<Problem>());
    for (    SNode rule : CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1164853e0faL,"jetbrains.mps.lang.typesystem.structure.NonTypesystemRule"),false))) {
      if (SNodeOperations.isInstanceOf(SLinkOperations.getTarget(rule,MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1117e7b5c73L,0x1117e7b9c40L,"applicableNode")),MetaAdapterFactory.getConcept(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1117e2c3e68L,"jetbrains.mps.lang.typesystem.structure.PatternCondition"))) {
        ListSequence.fromList(result).addElement(new MigrateManually("Checking rule with pattern condition. Use intention to move pattern inside the rule body.",rule));
      }
      if (SPropertyOperations.getBoolean(rule,MetaAdapterFactory.getProperty(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1164847e929L,0x116484991d1L,"overrides"))) {
        ListSequence.fromList(result).addElement(new MigrateManually("Rule with 'overrides' flag without explicitly enumerating rules to override",rule));
      }
    }
    return result;
  }
}
