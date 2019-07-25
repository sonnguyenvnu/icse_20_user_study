@Override public Iterable<Problem> check(SModule m){
{
    SearchScope scope_3c5dbg_a0e=CommandUtil.createScope(m);
    final SearchScope scope_3c5dbg_a0e_0=new EditableFilteringScope(scope_3c5dbg_a0e);
    final QueryExecutionContext context=new QueryExecutionContext(){
      public SearchScope getDefaultSearchScope(){
        return scope_3c5dbg_a0e_0;
      }
    }
;
    return Sequence.fromClosure(new ISequenceClosure<Problem>(){
      public Iterable<Problem> iterable(){
        return Sequence.fromIterable(CommandUtil.nodes(CommandUtil.selectScope(null,context))).where(new IWhereFilter<SNode>(){
          public boolean accept(          SNode it){
            return SNodeOperations.isInstanceOf(it,SNodeOperations.asSConcept(MetaAdapterFactory.getConcept(0xc0080a477e374558L,0xbee99ae18e690549L,0x33c018482cafa9d6L,"jetbrains.mps.lang.extension.structure.ExtensionPointDeclaration"))) || SNodeOperations.isInstanceOf(it,SNodeOperations.asSConcept(MetaAdapterFactory.getInterfaceConcept(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,"jetbrains.mps.lang.core.structure.INamedConcept")));
          }
        }
).where(new IWhereFilter<SNode>(){
          public boolean accept(          SNode it){
            return it.hasProperty(MetaAdapterFactory.getProperty(0xc0080a477e374558L,0xbee99ae18e690549L,0x33c018482cafa9d6L,0x520ae19dd2771b96L,"extensionName"));
          }
        }
).select(new ISelector<SNode,Problem>(){
          public Problem select(          SNode it){
            return DeprecatedConceptMemberNotMigratedProblem.deprecatedProperty(it,MetaAdapterFactory.getProperty(0xc0080a477e374558L,0xbee99ae18e690549L,0x33c018482cafa9d6L,0x520ae19dd2771b96L,"extensionName"));
          }
        }
);
      }
    }
);
  }
}
