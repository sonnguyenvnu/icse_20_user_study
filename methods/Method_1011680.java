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
    result=CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0x6106f6117a7442d1L,0x80deedc5c602bfd1L,0x5bdb7aaec13745e9L,"jetbrains.mps.lang.editor.diagram.structure.CreationActionReference"),false)).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return isNotEmptyString(SPropertyOperations.getString(it,MetaAdapterFactory.getProperty(0x6106f6117a7442d1L,0x80deedc5c602bfd1L,0x5bdb7aaec13745e9L,0x3cfdbd635b5afe8dL,"iconPath")));
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
