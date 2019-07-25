@Override public Iterable<Problem> check(SModule m){
{
    SearchScope scope_i4ro0d_a0e=CommandUtil.createScope(m);
    final SearchScope scope_i4ro0d_a0e_0=new EditableFilteringScope(scope_i4ro0d_a0e);
    QueryExecutionContext context=new QueryExecutionContext(){
      public SearchScope getDefaultSearchScope(){
        return scope_i4ro0d_a0e_0;
      }
    }
;
    return CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0x9de7c5ceea6f4fb4L,0xa7ba45e62b53cbadL,0x6aff2c1049316cdaL,"decl.structure.OldComponent"),false)).select(new ISelector<SNode,NotMigratedNode>(){
      public NotMigratedNode select(      SNode it){
        return new NotMigratedNode(it){
          public String getMessage(){
            return "old component should be replaced by a new one";
          }
        }
;
      }
    }
);
  }
}
