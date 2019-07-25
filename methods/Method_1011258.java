@Override public Iterable<Problem> check(SModule m){
  List<SNode> notMigrated=ListSequence.fromList(new ArrayList<SNode>());
{
    SearchScope scope_2kk9yi_b0e=CommandUtil.createScope(m);
    final SearchScope scope_2kk9yi_b0e_0=new EditableFilteringScope(scope_2kk9yi_b0e);
    QueryExecutionContext context=new QueryExecutionContext(){
      public SearchScope getDefaultSearchScope(){
        return scope_2kk9yi_b0e_0;
      }
    }
;
    ListSequence.fromList(notMigrated).addSequence(CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0xc0080a477e374558L,0xbee99ae18e690549L,0x33c018482cafa9d4L,"jetbrains.mps.lang.extension.structure.ExtensionDeclaration"),false)).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return !(SNodeOperations.is(SNodeOperations.getContainingRoot(it),new SNodePointer("r:30687ffb-4921-4796-841c-e217080d600a(jetbrains.mps.lang.extension.migration)","4174378934472392928")));
      }
    }
));
    ListSequence.fromList(notMigrated).addSequence(CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0xc0080a477e374558L,0xbee99ae18e690549L,0x61a62b43e15253efL,"jetbrains.mps.lang.extension.structure.ExtensionFunction"),false)).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return !(SNodeOperations.is(SNodeOperations.getContainingRoot(it),new SNodePointer("r:30687ffb-4921-4796-841c-e217080d600a(jetbrains.mps.lang.extension.migration)","4174378934472392928")));
      }
    }
));
    ListSequence.fromList(notMigrated).addSequence(CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0xc0080a477e374558L,0xbee99ae18e690549L,0x61a62b43e1534e99L,"jetbrains.mps.lang.extension.structure.ExtensionFieldDeclaration"),false)).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return !(SNodeOperations.is(SNodeOperations.getContainingRoot(it),new SNodePointer("r:30687ffb-4921-4796-841c-e217080d600a(jetbrains.mps.lang.extension.migration)","4174378934472392928")));
      }
    }
));
    ListSequence.fromList(notMigrated).addSequence(CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0xc0080a477e374558L,0xbee99ae18e690549L,0x61a62b43e1534e9eL,"jetbrains.mps.lang.extension.structure.ExtensionFieldReference"),false)).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return !(SNodeOperations.is(SNodeOperations.getContainingRoot(it),new SNodePointer("r:30687ffb-4921-4796-841c-e217080d600a(jetbrains.mps.lang.extension.migration)","4174378934472392928")));
      }
    }
));
    ListSequence.fromList(notMigrated).addSequence(CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0xc0080a477e374558L,0xbee99ae18e690549L,0x6f6f7f3b7a178565L,"jetbrains.mps.lang.extension.structure.ExtensionObjectGetter"),false)).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return !(SNodeOperations.is(SNodeOperations.getContainingRoot(it),new SNodePointer("r:30687ffb-4921-4796-841c-e217080d600a(jetbrains.mps.lang.extension.migration)","4174378934472392928")));
      }
    }
));
  }
  return ListSequence.fromList(notMigrated).select(new ISelector<SNode,Problem>(){
    public Problem select(    SNode it){
      Problem migrated=new DeprecatedConceptNotMigratedProblem(it);
      return migrated;
    }
  }
);
}
