public Iterable<Problem> check(){
{
    SearchScope scope_7l65wi_a0f=CommandUtil.createScope(module);
    final SearchScope scope_7l65wi_a0f_0=new EditableFilteringScope(scope_7l65wi_a0f);
    QueryExecutionContext context=new QueryExecutionContext(){
      public SearchScope getDefaultSearchScope(){
        return scope_7l65wi_a0f_0;
      }
    }
;
    return CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),SNodeOperations.asSConcept(concept),false)).select(new ISelector<SNode,Problem>(){
      public Problem select(      SNode it){
        return ((Problem)new DeprecatedConceptNotMigratedProblem(it));
      }
    }
);
  }
}
