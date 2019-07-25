public Collection<SNode> apply(@NotNull final TemplateExecutionEnvironment environment,@NotNull final TemplateContext context) throws GenerationException {
  Collection<SNode> tlist1=null;
  TemplateContext context1=context;
  context1=context1.withVariable("var:ConceptBehaviors",QueriesGenerated.varMacro_Value_3_0(new TemplateVarContext(context,new SNodePointer("r:229ce18d-2bb0-4d5b-a7cd-cec65841e459(jetbrains.mps.lang.behavior.generator.template.main@generator)","2409421742521899080"))));
  final SNode tnode2=environment.createOutputNode(myConcepts[0]);
  try {
    environment.nodeCopied(context1,tnode2,"tpl/r:229ce18d-2bb0-4d5b-a7cd-cec65841e459/2056529430201890935");
    SNodeAccessUtil.setProperty(tnode2,myProperties[0],"BehaviorAspectDescriptor");
    SNodeAccessUtil.setProperty(tnode2,myProperties[1],"true");
    TemplateContext context2=context1.subContext();
{
      final List<SNode> tlist3=new ArrayList<SNode>();
      final Iterable<SNode> loopList3=QueriesGenerated.sourceNodesQuery_3_0(new SourceSubstituteMacroNodesContext(context2,loopMacroRef_v965vj_b0a0a1a4a4a4));
      int loopIndex_behaviorCounter_3=0;
      for (      SNode itnode3 : loopList3) {
        if (itnode3 == null) {
          continue;
        }
        TemplateContext context3=context2.subContext("BHDescriptorField",itnode3);
        context3=context3.withVariable("cv:behaviorCounter",loopIndex_behaviorCounter_3++);
        final SNode tnode4=environment.createOutputNode(myConcepts[1]);
        try {
          environment.nodeCopied(context3,tnode4,"tpl/r:229ce18d-2bb0-4d5b-a7cd-cec65841e459/5630687994792687375");
          SNodeAccessUtil.setProperty(tnode4,myProperties[2],"false");
          SNodeAccessUtil.setProperty(tnode4,myProperties[3],"false");
          SNodeAccessUtil.setProperty(tnode4,myProperties[4],"true");
          SNodeAccessUtil.setProperty(tnode4,myProperties[0],TemplateUtil.asString(QueriesGenerated.propertyMacro_GetValue_3_0(new PropertyMacroContext(context3,"myBHDescriptor",propertyMacro_v965vj_c0a0c0e0e0d0e0e0e))));
          TemplateContext context4=context3.subContext();
{
            final SNode tnode5=environment.createOutputNode(myConcepts[2]);
            try {
            }
  finally {
            }
            tnode4.addChild(myAggregationLinks[0],tnode5);
          }
{
            final SNode tnode6=environment.createOutputNode(myConcepts[3]);
            try {
              tnode6.setReference(myAssociationLinks[0],SReference.create(myAssociationLinks[0],tnode6,PersistenceFacade.getInstance().createModelReference("d936855b-48da-4812-a8a0-2bfddd633ac5/java:jetbrains.mps.core.aspects.behaviour.api(jetbrains.mps.lang.behavior.api/)"),PersistenceFacade.getInstance().createNodeId("~BHDescriptor")));
            }
  finally {
            }
            tnode4.addChild(myAggregationLinks[1],tnode6);
          }
{
            final SNode tnode7=environment.createOutputNode(myConcepts[4]);
            try {
              TemplateContext context5=context4.subContext();
{
                final SNode tnode8=environment.createOutputNode(myConcepts[5]);
                try {
                  environment.resolve(new RefResolver(tnode8,myAssociationLinks[1],context5,new SNodePointer("r:229ce18d-2bb0-4d5b-a7cd-cec65841e459(jetbrains.mps.lang.behavior.generator.template.main@generator)","8560627202334867185"),"BaseBHDescriptor"){
                    @Override public Object resolve(){
                      return QueriesGenerated.referenceMacro_GetReferent_3_0(createQueryContext());
                    }
                  }
);
                }
  finally {
                }
                tnode7.addChild(myAggregationLinks[2],tnode8);
              }
            }
  finally {
            }
            tnode4.addChild(myAggregationLinks[3],tnode7);
          }
        }
  finally {
        }
        if (tnode4 != null) {
          environment.registerLabel(itnode3,tnode4,"BHDescriptorField");
          tlist3.add(tnode4);
        }
      }
      for (      SNode child9 : TemplateUtil.asNotNull(tlist3)) {
        tnode2.addChild(myAggregationLinks[4],child9);
      }
    }
{
      final SNode tnode10=environment.createOutputNode(myConcepts[6]);
      try {
      }
  finally {
      }
      tnode2.addChild(myAggregationLinks[4],tnode10);
    }
{
      final SNode tnode11=environment.createOutputNode(myConcepts[3]);
      try {
        tnode11.setReference(myAssociationLinks[0],SReference.create(myAssociationLinks[0],tnode11,PersistenceFacade.getInstance().createModelReference("d936855b-48da-4812-a8a0-2bfddd633ac4/java:jetbrains.mps.core.aspects.behaviour(jetbrains.mps.lang.behavior.runtime/)"),PersistenceFacade.getInstance().createNodeId("~BaseBehaviorAspectDescriptor")));
      }
  finally {
      }
      tnode2.addChild(myAggregationLinks[5],tnode11);
    }
{
      final SNode tnode12=environment.createOutputNode(myConcepts[7]);
      try {
        environment.nodeCopied(context2,tnode12,"tpl/r:229ce18d-2bb0-4d5b-a7cd-cec65841e459/9221686591909436657");
        TemplateContext context6=context2.subContext();
{
          final SNode tnode13=environment.createOutputNode(myConcepts[8]);
          try {
          }
  finally {
          }
          tnode12.addChild(myAggregationLinks[6],tnode13);
        }
{
          final SNode tnode14=environment.createOutputNode(myConcepts[9]);
          try {
          }
  finally {
          }
          tnode12.addChild(myAggregationLinks[7],tnode14);
        }
{
          final SNode tnode15=environment.createOutputNode(myConcepts[10]);
          try {
          }
  finally {
          }
          tnode12.addChild(myAggregationLinks[0],tnode15);
        }
      }
  finally {
      }
      tnode2.addChild(myAggregationLinks[4],tnode12);
    }
{
      final SNode tnode16=environment.createOutputNode(myConcepts[6]);
      try {
      }
  finally {
      }
      tnode2.addChild(myAggregationLinks[4],tnode16);
    }
{
      final SNode tnode17=environment.createOutputNode(myConcepts[11]);
      try {
        SNodeAccessUtil.setProperty(tnode17,myProperties[5],"false");
        SNodeAccessUtil.setProperty(tnode17,myProperties[0],"getDescriptor");
        SNodeAccessUtil.setProperty(tnode17,myProperties[6],"false");
        TemplateContext context7=context2.subContext();
{
          final SNode tnode18=environment.createOutputNode(myConcepts[10]);
          try {
          }
  finally {
          }
          tnode17.addChild(myAggregationLinks[0],tnode18);
        }
{
          final SNode tnode19=environment.createOutputNode(myConcepts[3]);
          try {
            tnode19.setReference(myAssociationLinks[0],SReference.create(myAssociationLinks[0],tnode19,PersistenceFacade.getInstance().createModelReference("d936855b-48da-4812-a8a0-2bfddd633ac5/java:jetbrains.mps.core.aspects.behaviour.api(jetbrains.mps.lang.behavior.api/)"),PersistenceFacade.getInstance().createNodeId("~BHDescriptor")));
          }
  finally {
          }
          tnode17.addChild(myAggregationLinks[6],tnode19);
        }
{
          final SNode tnode20=environment.createOutputNode(myConcepts[12]);
          try {
            environment.nodeCopied(context7,tnode20,"tpl/r:229ce18d-2bb0-4d5b-a7cd-cec65841e459/7385785963572924722");
            SNodeAccessUtil.setProperty(tnode20,myProperties[0],"concept");
            TemplateContext context8=context7.subContext();
{
              final SNode tnode21=environment.createOutputNode(myConcepts[13]);
              try {
              }
  finally {
              }
              tnode20.addChild(myAggregationLinks[1],tnode21);
            }
{
              final SNode tnode22=environment.createOutputNode(myConcepts[14]);
              try {
                tnode22.setReference(myAssociationLinks[2],SReference.create(myAssociationLinks[2],tnode22,PersistenceFacade.getInstance().createModelReference("3f233e7f-b8a6-46d2-a57f-795d56775243/java:org.jetbrains.annotations(Annotations/)"),PersistenceFacade.getInstance().createNodeId("~NotNull")));
              }
  finally {
              }
              tnode20.addChild(myAggregationLinks[8],tnode22);
            }
          }
  finally {
          }
          tnode17.addChild(myAggregationLinks[9],tnode20);
        }
{
          final SNode tnode23=environment.createOutputNode(myConcepts[14]);
          try {
            tnode23.setReference(myAssociationLinks[2],SReference.create(myAssociationLinks[2],tnode23,PersistenceFacade.getInstance().createModelReference("3f233e7f-b8a6-46d2-a57f-795d56775243/java:org.jetbrains.annotations(Annotations/)"),PersistenceFacade.getInstance().createNodeId("~Nullable")));
          }
  finally {
          }
          tnode17.addChild(myAggregationLinks[8],tnode23);
        }
{
          final SNode tnode24=environment.createOutputNode(myConcepts[9]);
          try {
            TemplateContext context9=context7.subContext();
{
              final SNode tnode25=environment.createOutputNode(myConcepts[15]);
              try {
                TemplateContext context10=context9.subContext();
{
                  final SNode tnode26=environment.createOutputNode(myConcepts[16]);
                  try {
                    environment.resolveInTemplateLater(tnode26,myAssociationLinks[3],templateNode_v965vj_c0a0a1a1a1a1a1a8a1a9a4a4,"tpl/r:229ce18d-2bb0-4d5b-a7cd-cec65841e459/7385785963572924722","concept",context10);
                  }
  finally {
                  }
                  tnode25.addChild(myAggregationLinks[10],tnode26);
                }
{
                  final List<SNode> tlist27=new ArrayList<SNode>();
                  final Iterable<SNode> loopList27=QueriesGenerated.sourceNodesQuery_3_1(new SourceSubstituteMacroNodesContext(context10,loopMacroRef_v965vj_b0a0a1a2a1a1a1a8a1a9a4a4));
                  for (                  SNode itnode27 : loopList27) {
                    if (itnode27 == null) {
                      continue;
                    }
                    TemplateContext context11=context10.subContext(itnode27);
                    final SNode tnode28=environment.createOutputNode(myConcepts[17]);
                    try {
                      TemplateContext context12=context11.subContext();
{
                        final SNode tnode29=environment.createOutputNode(myConcepts[9]);
                        try {
                          TemplateContext context13=context12.subContext();
{
                            final SNode tnode30=environment.createOutputNode(myConcepts[18]);
                            try {
                              TemplateContext context14=context13.subContext();
{
                                final SNode tnode31=environment.createOutputNode(myConcepts[16]);
                                try {
                                  environment.resolve(new RefResolver(tnode31,myAssociationLinks[3],context14,new SNodePointer("r:229ce18d-2bb0-4d5b-a7cd-cec65841e459(jetbrains.mps.lang.behavior.generator.template.main@generator)","7600007869448722163"),"myBHDescriptor"){
                                    @Override public Object resolve(){
                                      return QueriesGenerated.referenceMacro_GetReferent_3_1(createQueryContext());
                                    }
                                  }
);
                                }
  finally {
                                }
                                tnode30.addChild(myAggregationLinks[11],tnode31);
                              }
                            }
  finally {
                            }
                            tnode29.addChild(myAggregationLinks[12],tnode30);
                          }
                        }
  finally {
                        }
                        tnode28.addChild(myAggregationLinks[13],tnode29);
                      }
{
                        final SNode tnode32=environment.createOutputNode(myConcepts[19]);
                        try {
                          environment.resolve(new RefResolver(tnode32,myAssociationLinks[4],context12,new SNodePointer("r:229ce18d-2bb0-4d5b-a7cd-cec65841e459(jetbrains.mps.lang.behavior.generator.template.main@generator)","6551427688186219814"),"BaseConcept"){
                            @Override public Object resolve(){
                              return QueriesGenerated.referenceMacro_GetReferent_3_2(createQueryContext());
                            }
                          }
);
                        }
  finally {
                        }
                        tnode28.addChild(myAggregationLinks[14],tnode32);
                      }
                    }
  finally {
                    }
                    if (tnode28 != null) {
                      tlist27.add(tnode28);
                    }
                  }
                  for (                  SNode child33 : TemplateUtil.asNotNull(tlist27)) {
                    tnode25.addChild(myAggregationLinks[15],child33);
                  }
                }
{
                  final SNode tnode34=environment.createOutputNode(myConcepts[9]);
                  try {
                  }
  finally {
                  }
                  tnode25.addChild(myAggregationLinks[16],tnode34);
                }
              }
  finally {
              }
              tnode24.addChild(myAggregationLinks[12],tnode25);
            }
{
              final SNode tnode35=environment.createOutputNode(myConcepts[18]);
              try {
                TemplateContext context15=context9.subContext();
{
                  final SNode tnode36=environment.createOutputNode(myConcepts[20]);
                  try {
                  }
  finally {
                  }
                  tnode35.addChild(myAggregationLinks[11],tnode36);
                }
              }
  finally {
              }
              tnode24.addChild(myAggregationLinks[12],tnode35);
            }
          }
  finally {
          }
          tnode17.addChild(myAggregationLinks[7],tnode24);
        }
      }
  finally {
      }
      tnode2.addChild(myAggregationLinks[4],tnode17);
    }
{
      final SNode tnode37=environment.createOutputNode(myConcepts[10]);
      try {
      }
  finally {
      }
      tnode2.addChild(myAggregationLinks[0],tnode37);
    }
  }
  finally {
  }
  tlist1=TemplateUtil.singletonList(tnode2);
  return tlist1;
}
