@Override public Iterable<Problem> check(SModule m){
{
    SearchScope scope_u457zm_a0e=CommandUtil.createScope(m);
    final SearchScope scope_u457zm_a0e_0=new EditableFilteringScope(scope_u457zm_a0e);
    QueryExecutionContext context=new QueryExecutionContext(){
      public SearchScope getDefaultSearchScope(){
        return scope_u457zm_a0e_0;
      }
    }
;
    return CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0xd3d2b6e3a4b343d5L,0xbb29420d39fa86abL,0x6aff2c104931574dL,"ref.structure.OldComponentRef"),false)).select(new ISelector<SNode,NotMigratedNode>(){
      public NotMigratedNode select(      SNode it){
        return new NotMigratedNode(it){
          public String getMessage(){
            return "old references should be replaced by a new one";
          }
        }
;
      }
    }
);
  }
}
