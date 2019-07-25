@Override public Iterable<Problem> check(SModule m){
  List<Problem> result=ListSequence.fromList(new ArrayList<Problem>());
{
    SearchScope scope_qvpvui_b0e=CommandUtil.createScope(m);
    final SearchScope scope_qvpvui_b0e_0=new EditableFilteringScope(scope_qvpvui_b0e);
    QueryExecutionContext context=new QueryExecutionContext(){
      public SearchScope getDefaultSearchScope(){
        return scope_qvpvui_b0e_0;
      }
    }
;
    ListSequence.fromList(result).addSequence(CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x7b0da3c650be8558L,"jetbrains.mps.lang.smodel.structure.AsNodeOperation"),false)).select(new ISelector<SNode,Problem>(){
      public Problem select(      SNode it){
        return ((Problem)new NotMigratedNode(it){
          public String getMessage(){
            return "Not migrated .asNode operation";
          }
        }
);
      }
    }
));
    ListSequence.fromList(result).addSequence(CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x110f3e65fdcL,"jetbrains.mps.lang.smodel.structure.Node_GetConceptOperation"),false)).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        SNode p=SNodeOperations.as(SNodeOperations.getParent(it),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x116b46a08c4L,"jetbrains.mps.baseLanguage.structure.DotExpression"));
        if (p == null) {
          return false;
        }
        return SConceptOperations.isSubConceptOf(SNodeOperations.asSConcept(SNodeOperations.getConcept(SLinkOperations.getTarget(p,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x116b46a08c4L,0x116b46b36c4L,"operation")))),MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x1090ea2ebacL,"jetbrains.mps.lang.smodel.structure.SNodeOperation")) && (boolean)SNodeOperation__BehaviorDescriptor.applicableToSConcept_id7E3Sw0HhwkZ.invoke(SNodeOperations.asSConcept(SNodeOperations.castConcept(SNodeOperations.getConcept(SLinkOperations.getTarget(p,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x116b46a08c4L,0x116b46b36c4L,"operation"))),MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x1090ea2ebacL,"jetbrains.mps.lang.smodel.structure.SNodeOperation"))));
      }
    }
).select(new ISelector<SNode,Problem>(){
      public Problem select(      SNode it){
        return ((Problem)new NotMigratedNode(it){
          public String getMessage(){
            return "Not migrated .conceptNode operation";
          }
        }
);
      }
    }
));
    ListSequence.fromList(result).addSequence(CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x110f3e65fdcL,"jetbrains.mps.lang.smodel.structure.Node_GetConceptOperation"),false)).select(new ISelector<SNode,Problem>(){
      public Problem select(      SNode it){
        return ((Problem)new NotMigratedNode(it){
          public String getMessage(){
            return "Not migrated .conceptNode operation";
          }
        }
);
      }
    }
));
  }
  return result;
}
