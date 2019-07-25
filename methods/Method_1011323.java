@Override public Iterable<Problem> check(SModule m){
  Iterable<Problem> result;
{
    SearchScope scope_8o28b_b0e=CommandUtil.createScope(m);
    final SearchScope scope_8o28b_b0e_0=new EditableFilteringScope(scope_8o28b_b0e);
    QueryExecutionContext context=new QueryExecutionContext(){
      public SearchScope getDefaultSearchScope(){
        return scope_8o28b_b0e_0;
      }
    }
;
    result=CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0x28f9e4973b424291L,0xaeba0a1039153ab1L,0x119e269a79fL,"jetbrains.mps.lang.plugin.structure.PreferencePage"),false)).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return isNotEmptyString(SPropertyOperations.getString(it,MetaAdapterFactory.getProperty(0x28f9e4973b424291L,0xaeba0a1039153ab1L,0x119e269a79fL,0x119e28e412bL,"iconPath")));
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
