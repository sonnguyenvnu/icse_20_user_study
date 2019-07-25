@Override public Iterable<Problem> check(SModule m){
{
    SearchScope scope_i7tkk0_a0e=CommandUtil.createScope(m);
    final SearchScope scope_i7tkk0_a0e_0=new EditableFilteringScope(scope_i7tkk0_a0e);
    QueryExecutionContext context=new QueryExecutionContext(){
      public SearchScope getDefaultSearchScope(){
        return scope_i7tkk0_a0e_0;
      }
    }
;
    List<Problem> result=ListSequence.fromList(new ArrayList<Problem>());
    ListSequence.fromList(result).addSequence(CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0xf280165065d5424eL,0xbb1b463a8781b786L,0x1cb65d9fe66a764cL,"jetbrains.mps.baseLanguage.javadoc.structure.ClassifierDocComment"),false)).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return ListSequence.fromList(SLinkOperations.getChildren(it,MetaAdapterFactory.getContainmentLink(0xf280165065d5424eL,0xbb1b463a8781b786L,0x1cb65d9fe66a764cL,0x1cb65d9fe66a764eL,"param"))).isNotEmpty();
      }
    }
).select(new ISelector<SNode,Problem>(){
      public Problem select(      SNode it){
        return DeprecatedConceptMemberNotMigratedProblem.deprecatedContainmentLink(it,MetaAdapterFactory.getContainmentLink(0xf280165065d5424eL,0xbb1b463a8781b786L,0x1cb65d9fe66a764cL,0x1cb65d9fe66a764eL,"param"));
      }
    }
));
    ListSequence.fromList(result).addSequence(CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0xf280165065d5424eL,0xbb1b463a8781b786L,0x4a3c146b7faeeb34L,"jetbrains.mps.baseLanguage.javadoc.structure.MethodDocComment"),false)).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return ListSequence.fromList(SLinkOperations.getChildren(it,MetaAdapterFactory.getContainmentLink(0xf280165065d5424eL,0xbb1b463a8781b786L,0x4a3c146b7faeeb34L,0x757ba20a4c90eaf9L,"param"))).isNotEmpty();
      }
    }
).select(new ISelector<SNode,Problem>(){
      public Problem select(      SNode it){
        return DeprecatedConceptMemberNotMigratedProblem.deprecatedContainmentLink(it,MetaAdapterFactory.getContainmentLink(0xf280165065d5424eL,0xbb1b463a8781b786L,0x4a3c146b7faeeb34L,0x757ba20a4c90eaf9L,"param"));
      }
    }
));
    ListSequence.fromList(result).addSequence(CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0xf280165065d5424eL,0xbb1b463a8781b786L,0x4a3c146b7faeeb34L,"jetbrains.mps.baseLanguage.javadoc.structure.MethodDocComment"),false)).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return ListSequence.fromList(SLinkOperations.getChildren(it,MetaAdapterFactory.getContainmentLink(0xf280165065d5424eL,0xbb1b463a8781b786L,0x4a3c146b7faeeb34L,0x514c0f687050918cL,"throwsTag"))).isNotEmpty();
      }
    }
).select(new ISelector<SNode,Problem>(){
      public Problem select(      SNode it){
        return DeprecatedConceptMemberNotMigratedProblem.deprecatedContainmentLink(it,MetaAdapterFactory.getContainmentLink(0xf280165065d5424eL,0xbb1b463a8781b786L,0x4a3c146b7faeeb34L,0x514c0f687050918cL,"throwsTag"));
      }
    }
));
    ListSequence.fromList(result).addSequence(CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0xf280165065d5424eL,0xbb1b463a8781b786L,0x4a3c146b7faeeb34L,"jetbrains.mps.baseLanguage.javadoc.structure.MethodDocComment"),false)).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return (SLinkOperations.getTarget(it,MetaAdapterFactory.getContainmentLink(0xf280165065d5424eL,0xbb1b463a8781b786L,0x4a3c146b7faeeb34L,0x514c0f6870509198L,"return")) != null);
      }
    }
).select(new ISelector<SNode,Problem>(){
      public Problem select(      SNode it){
        return DeprecatedConceptMemberNotMigratedProblem.deprecatedContainmentLink(it,MetaAdapterFactory.getContainmentLink(0xf280165065d5424eL,0xbb1b463a8781b786L,0x4a3c146b7faeeb34L,0x514c0f6870509198L,"return"));
      }
    }
));
    ListSequence.fromList(result).addSequence(CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0xf280165065d5424eL,0xbb1b463a8781b786L,0x4a3c146b7fae70d3L,"jetbrains.mps.baseLanguage.javadoc.structure.BaseDocComment"),false)).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return (SLinkOperations.getTarget(it,MetaAdapterFactory.getContainmentLink(0xf280165065d5424eL,0xbb1b463a8781b786L,0x4a3c146b7fae70d3L,0x757ba20a4c87f96bL,"deprecated")) != null);
      }
    }
).select(new ISelector<SNode,Problem>(){
      public Problem select(      SNode it){
        return DeprecatedConceptMemberNotMigratedProblem.deprecatedContainmentLink(it,MetaAdapterFactory.getContainmentLink(0xf280165065d5424eL,0xbb1b463a8781b786L,0x4a3c146b7fae70d3L,0x757ba20a4c87f96bL,"deprecated"));
      }
    }
));
    ListSequence.fromList(result).addSequence(CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0xf280165065d5424eL,0xbb1b463a8781b786L,0x4a3c146b7fae70d3L,"jetbrains.mps.baseLanguage.javadoc.structure.BaseDocComment"),false)).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return ListSequence.fromList(SLinkOperations.getChildren(it,MetaAdapterFactory.getContainmentLink(0xf280165065d5424eL,0xbb1b463a8781b786L,0x4a3c146b7fae70d3L,0x4a3c146b7faeeb32L,"author"))).isNotEmpty();
      }
    }
).select(new ISelector<SNode,Problem>(){
      public Problem select(      SNode it){
        return DeprecatedConceptMemberNotMigratedProblem.deprecatedContainmentLink(it,MetaAdapterFactory.getContainmentLink(0xf280165065d5424eL,0xbb1b463a8781b786L,0x4a3c146b7fae70d3L,0x4a3c146b7faeeb32L,"author"));
      }
    }
));
    ListSequence.fromList(result).addSequence(CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0xf280165065d5424eL,0xbb1b463a8781b786L,0x4a3c146b7fae70d3L,"jetbrains.mps.baseLanguage.javadoc.structure.BaseDocComment"),false)).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return ListSequence.fromList(SLinkOperations.getChildren(it,MetaAdapterFactory.getContainmentLink(0xf280165065d5424eL,0xbb1b463a8781b786L,0x4a3c146b7fae70d3L,0x757ba20a4c87f962L,"since"))).isNotEmpty();
      }
    }
).select(new ISelector<SNode,Problem>(){
      public Problem select(      SNode it){
        return DeprecatedConceptMemberNotMigratedProblem.deprecatedContainmentLink(it,MetaAdapterFactory.getContainmentLink(0xf280165065d5424eL,0xbb1b463a8781b786L,0x4a3c146b7fae70d3L,0x757ba20a4c87f962L,"since"));
      }
    }
));
    ListSequence.fromList(result).addSequence(CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0xf280165065d5424eL,0xbb1b463a8781b786L,0x4a3c146b7fae70d3L,"jetbrains.mps.baseLanguage.javadoc.structure.BaseDocComment"),false)).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return ListSequence.fromList(SLinkOperations.getChildren(it,MetaAdapterFactory.getContainmentLink(0xf280165065d5424eL,0xbb1b463a8781b786L,0x4a3c146b7fae70d3L,0x757ba20a4c87f963L,"version"))).isNotEmpty();
      }
    }
).select(new ISelector<SNode,Problem>(){
      public Problem select(      SNode it){
        return DeprecatedConceptMemberNotMigratedProblem.deprecatedContainmentLink(it,MetaAdapterFactory.getContainmentLink(0xf280165065d5424eL,0xbb1b463a8781b786L,0x4a3c146b7fae70d3L,0x757ba20a4c87f963L,"version"));
      }
    }
));
    ListSequence.fromList(result).addSequence(CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0xf280165065d5424eL,0xbb1b463a8781b786L,0x4a3c146b7fae70d3L,"jetbrains.mps.baseLanguage.javadoc.structure.BaseDocComment"),false)).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return ListSequence.fromList(SLinkOperations.getChildren(it,MetaAdapterFactory.getContainmentLink(0xf280165065d5424eL,0xbb1b463a8781b786L,0x4a3c146b7fae70d3L,0x1ec532ec252df7ddL,"see"))).isNotEmpty();
      }
    }
).select(new ISelector<SNode,Problem>(){
      public Problem select(      SNode it){
        return DeprecatedConceptMemberNotMigratedProblem.deprecatedContainmentLink(it,MetaAdapterFactory.getContainmentLink(0xf280165065d5424eL,0xbb1b463a8781b786L,0x4a3c146b7fae70d3L,0x1ec532ec252df7ddL,"see"));
      }
    }
));
    return result;
  }
}
