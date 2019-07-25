@Override public Iterable<Problem> check(SModule m){
{
    SearchScope scope_gt4fl_a0e=CommandUtil.createScope(m);
    final SearchScope scope_gt4fl_a0e_0=new EditableFilteringScope(scope_gt4fl_a0e);
    final QueryExecutionContext context=new QueryExecutionContext(){
      public SearchScope getDefaultSearchScope(){
        return scope_gt4fl_a0e_0;
      }
    }
;
    return Sequence.fromClosure(new ISequenceClosure<Problem>(){
      public Iterable<Problem> iterable(){
        return Sequence.fromIterable(CommandUtil.nodes(CommandUtil.selectScope(null,context))).where(new IWhereFilter<SNode>(){
          public boolean accept(          SNode it){
            return SNodeOperations.isInstanceOf(it,SNodeOperations.asSConcept(MetaAdapterFactory.getConcept(MetaAdapterFactory.getLanguage(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,"jetbrains.mps.lang.typesystem"),0x1164847e929L,"AbstractCheckingRule"))) || SNodeOperations.isInstanceOf(it,SNodeOperations.asSConcept(MetaAdapterFactory.getConcept(MetaAdapterFactory.getLanguage(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,"jetbrains.mps.lang.typesystem"),0x1117e2f5efaL,"InferenceRule")));
          }
        }
).where(new IWhereFilter<SNode>(){
          public boolean accept(          SNode it){
            return it.getChildren(MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1164847e929L,0x1885777d137135fcL,"overridesFun_old")).iterator().hasNext();
          }
        }
).select(new ISelector<SNode,Problem>(){
          public Problem select(          SNode it){
            return DeprecatedConceptMemberNotMigratedProblem.deprecatedContainmentLink(it,MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1164847e929L,0x1885777d137135fcL,"overridesFun_old"));
          }
        }
);
      }
    }
);
  }
}
