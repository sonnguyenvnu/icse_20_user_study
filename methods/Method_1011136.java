@Override public Iterable<Problem> check(SModule m){
{
    SearchScope scope_fgay1s_a0e=CommandUtil.createScope(m);
    final SearchScope scope_fgay1s_a0e_0=new EditableFilteringScope(scope_fgay1s_a0e);
    QueryExecutionContext context=new QueryExecutionContext(){
      public SearchScope getDefaultSearchScope(){
        return scope_fgay1s_a0e_0;
      }
    }
;
    return CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0xf280165065d5424eL,0xbb1b463a8781b786L,0x757ba20a4c87f964L,"jetbrains.mps.baseLanguage.javadoc.structure.DeprecatedBlockDocTag"),false)).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return !(SNodeOperations.is(SNodeOperations.getContainingRoot(it),new SNodePointer("r:2cad94ae-7a5e-484a-a104-c211cb3b0451(jetbrains.mps.baseLanguage.javadoc.migration)","4787009421625779235")));
      }
    }
).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return (SLinkOperations.getTarget(it,MetaAdapterFactory.getContainmentLink(0xf280165065d5424eL,0xbb1b463a8781b786L,0x757ba20a4c87f964L,0x250631c6c859e113L,"text")) == null);
      }
    }
).select(new ISelector<SNode,Problem>(){
      public Problem select(      SNode it){
        return ((Problem)new NotMigratedNode(it){
          public String getMessage(){
            return "Deprecated annotation with empty text was not migrated";
          }
        }
);
      }
    }
);
  }
}
