@Override public Iterable<Problem> check(SModule m){
{
    SearchScope scope_xqhmgi_a0e=CommandUtil.createScope(m);
    final SearchScope scope_xqhmgi_a0e_0=new EditableFilteringScope(scope_xqhmgi_a0e);
    QueryExecutionContext context=new QueryExecutionContext(){
      public SearchScope getDefaultSearchScope(){
        return scope_xqhmgi_a0e_0;
      }
    }
;
    List<Problem> problems=ListSequence.fromList(new ArrayList<Problem>());
    ListSequence.fromList(problems).addSequence(CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x120c01735d3L,"jetbrains.mps.lang.smodel.structure.EnumMember_NameOperation_Old"),false)).select(new ISelector<SNode,DeprecatedConceptNotMigratedProblem>(){
      public DeprecatedConceptNotMigratedProblem select(      SNode it){
        return new DeprecatedConceptNotMigratedProblem(it);
      }
    }
));
    ListSequence.fromList(problems).addSequence(CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x120bff92dbeL,"jetbrains.mps.lang.smodel.structure.EnumMember_ValueOperation_Old"),false)).select(new ISelector<SNode,DeprecatedConceptNotMigratedProblem>(){
      public DeprecatedConceptNotMigratedProblem select(      SNode it){
        return new DeprecatedConceptNotMigratedProblem(it);
      }
    }
));
    ListSequence.fromList(problems).addSequence(CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x1091e6212fdL,"jetbrains.mps.lang.smodel.structure.EnumMemberReference_Old"),false)).select(new ISelector<SNode,DeprecatedConceptNotMigratedProblem>(){
      public DeprecatedConceptNotMigratedProblem select(      SNode it){
        return new DeprecatedConceptNotMigratedProblem(it);
      }
    }
));
    ListSequence.fromList(problems).addSequence(CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x120ed32e98bL,"jetbrains.mps.lang.smodel.structure.SEnumOperationInvocation"),false)).select(new ISelector<SNode,DeprecatedConceptNotMigratedProblem>(){
      public DeprecatedConceptNotMigratedProblem select(      SNode it){
        return new DeprecatedConceptNotMigratedProblem(it);
      }
    }
));
    ListSequence.fromList(problems).addSequence(CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getInterfaceConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x120ed37273dL,"jetbrains.mps.lang.smodel.structure.SEnumOperation_Old"),false)).select(new ISelector<SNode,DeprecatedConceptNotMigratedProblem>(){
      public DeprecatedConceptNotMigratedProblem select(      SNode it){
        return new DeprecatedConceptNotMigratedProblem(it);
      }
    }
));
    ListSequence.fromList(problems).addSequence(CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x60c7f83bafd83b5bL,"jetbrains.mps.lang.smodel.structure.EnumMemberValueRefExpression"),false)).select(new ISelector<SNode,DeprecatedConceptNotMigratedProblem>(){
      public DeprecatedConceptNotMigratedProblem select(      SNode it){
        return new DeprecatedConceptNotMigratedProblem(it);
      }
    }
));
    ListSequence.fromList(problems).addSequence(CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x1091e6212fdL,"jetbrains.mps.lang.smodel.structure.EnumMemberReference_Old"),false)).select(new ISelector<SNode,DeprecatedConceptNotMigratedProblem>(){
      public DeprecatedConceptNotMigratedProblem select(      SNode it){
        return new DeprecatedConceptNotMigratedProblem(it);
      }
    }
));
    return problems;
  }
}
