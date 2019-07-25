public Collection<SNode> apply(@NotNull final TemplateExecutionEnvironment environment,@NotNull final TemplateContext context) throws GenerationException {
  final SNode tnode1=environment.createOutputNode(myConcepts[0]);
  try {
    environment.nodeCopied(context,tnode1,"tpl/r:3ab3501c-2f4b-48e6-9b6c-e31ff8ef3185/3308300503039570165");
    SNodeAccessUtil.setProperty(tnode1,myProperties[0],TemplateUtil.asString(QueriesGenerated.propertyMacro_GetValue_1_0(new PropertyMacroContext(context,"map_Script",propertyMacro_rbrxtl_c0a0c0b0b0e))));
    TemplateContext context1=context.subContext();
{
      final SNode tnode2=environment.createOutputNode(myConcepts[1]);
      try {
      }
  finally {
      }
      tnode1.addChild(myAggregationLinks[0],tnode2);
    }
{
      final SNode tnode3=environment.createOutputNode(myConcepts[2]);
      try {
        tnode3.setReference(myAssociationLinks[0],SReference.create(myAssociationLinks[0],tnode3,PersistenceFacade.getInstance().createModelReference("r:f5e9b11f-5073-4786-8ed1-a9e42307c3f8(JavaKaja.runtime)"),PersistenceFacade.getInstance().createNodeId("3308300503039473785")));
      }
  finally {
      }
      tnode1.addChild(myAggregationLinks[1],tnode3);
    }
{
      final SNode tnode4=environment.createOutputNode(myConcepts[3]);
      try {
        environment.nodeCopied(context1,tnode4,"tpl/r:3ab3501c-2f4b-48e6-9b6c-e31ff8ef3185/3308300503039570167");
        TemplateContext context2=context1.subContext();
{
          final SNode tnode5=environment.createOutputNode(myConcepts[4]);
          try {
          }
  finally {
          }
          tnode4.addChild(myAggregationLinks[2],tnode5);
        }
{
          final SNode tnode6=environment.createOutputNode(myConcepts[1]);
          try {
          }
  finally {
          }
          tnode4.addChild(myAggregationLinks[0],tnode6);
        }
{
          final SNode tnode7=environment.createOutputNode(myConcepts[5]);
          try {
          }
  finally {
          }
          tnode4.addChild(myAggregationLinks[3],tnode7);
        }
      }
  finally {
      }
      tnode1.addChild(myAggregationLinks[4],tnode4);
    }
{
      final SNode tnode8=environment.createOutputNode(myConcepts[6]);
      try {
        SNodeAccessUtil.setProperty(tnode8,myProperties[1],"false");
        SNodeAccessUtil.setProperty(tnode8,myProperties[0],"perform");
        TemplateContext context3=context1.subContext();
{
          final SNode tnode9=environment.createOutputNode(myConcepts[4]);
          try {
          }
  finally {
          }
          tnode8.addChild(myAggregationLinks[2],tnode9);
        }
{
          final SNode tnode10=environment.createOutputNode(myConcepts[7]);
          try {
          }
  finally {
          }
          tnode8.addChild(myAggregationLinks[0],tnode10);
        }
{
          final SNode tnode11=environment.createOutputNode(myConcepts[5]);
          try {
            TemplateContext context4=context3.subContext();
{
              final List<SNode> tlist12=new ArrayList<SNode>();
              final Iterable<SNode> loopList12=QueriesGenerated.sourceNodesQuery_1_0(new SourceSubstituteMacroNodesContext(context4,loopMacroRef_rbrxtl_b0a0a1a1a1a5a1a6a1a4));
              for (              SNode itnode12 : loopList12) {
                if (itnode12 == null) {
                  continue;
                }
                TemplateContext context5=context4.subContext(itnode12);
                Collection<SNode> tlist13=null;
                final SNode copySrcInput13=context5.getInput();
                tlist13=environment.copyNodes(TemplateUtil.singletonList(copySrcInput13),copySrcMacro_rbrxtl_b0a0e0c0b0b0f0b0g0b0e,"tpl/r:3ab3501c-2f4b-48e6-9b6c-e31ff8ef3185/3308300503039571100",context5);
                if (tlist13 != null) {
                  tlist12.addAll(tlist13);
                }
              }
              for (              SNode child14 : TemplateUtil.asNotNull(tlist12)) {
                tnode11.addChild(myAggregationLinks[5],child14);
              }
            }
          }
  finally {
          }
          tnode8.addChild(myAggregationLinks[3],tnode11);
        }
      }
  finally {
      }
      tnode1.addChild(myAggregationLinks[4],tnode8);
    }
{
      final List<SNode> tlist15=new ArrayList<SNode>();
      final Iterable<SNode> loopList15=QueriesGenerated.sourceNodesQuery_1_1(new SourceSubstituteMacroNodesContext(context1,loopMacroRef_rbrxtl_b0a0a1a7a1a4));
      for (      SNode itnode15 : loopList15) {
        if (itnode15 == null) {
          continue;
        }
        TemplateContext context6=context1.subContext(itnode15);
        Collection<SNode> tlist16=null;
        final SNode copySrcInput16=context6.getInput();
        tlist16=environment.copyNodes(TemplateUtil.singletonList(copySrcInput16),copySrcMacro_rbrxtl_b0a0e0c0h0b0e,"tpl/r:3ab3501c-2f4b-48e6-9b6c-e31ff8ef3185/3308300503039720831",context6);
        if (tlist16 != null) {
          tlist15.addAll(tlist16);
        }
      }
      for (      SNode child17 : TemplateUtil.asNotNull(tlist15)) {
        tnode1.addChild(myAggregationLinks[4],child17);
      }
    }
{
      final List<SNode> tlist18=new ArrayList<SNode>();
      final Iterable<SNode> loopList18=QueriesGenerated.sourceNodesQuery_1_2(new SourceSubstituteMacroNodesContext(context1,loopMacroRef_rbrxtl_b0a0a1a8a1a4));
      for (      SNode itnode18 : loopList18) {
        if (itnode18 == null) {
          continue;
        }
        TemplateContext context7=context1.subContext(itnode18);
        Collection<SNode> tlist19=null;
        final Iterable<SNode> copyListInput19=QueriesGenerated.sourceNodesQuery_1_3(new SourceSubstituteMacroNodesContext(context7,copySrcListMacro_rbrxtl_b0a0a3a2a8a1a4));
        tlist19=environment.copyNodes(copyListInput19,copySrcListMacro_rbrxtl_b0a0a3a2a8a1a4,"tpl/r:3ab3501c-2f4b-48e6-9b6c-e31ff8ef3185/4394627182934963449",context7);
        if (tlist19 != null) {
          tlist18.addAll(tlist19);
        }
      }
      for (      SNode child20 : TemplateUtil.asNotNull(tlist18)) {
        tnode1.addChild(myAggregationLinks[4],child20);
      }
    }
{
      final SNode tnode21=environment.createOutputNode(myConcepts[8]);
      try {
        SNodeAccessUtil.setProperty(tnode21,myProperties[0],"main");
        TemplateContext context8=context1.subContext();
{
          final SNode tnode22=environment.createOutputNode(myConcepts[4]);
          try {
          }
  finally {
          }
          tnode21.addChild(myAggregationLinks[2],tnode22);
        }
{
          final SNode tnode23=environment.createOutputNode(myConcepts[1]);
          try {
          }
  finally {
          }
          tnode21.addChild(myAggregationLinks[0],tnode23);
        }
{
          final SNode tnode24=environment.createOutputNode(myConcepts[5]);
          try {
            TemplateContext context9=context8.subContext();
{
              final SNode tnode25=environment.createOutputNode(myConcepts[9]);
              try {
                TemplateContext context10=context9.subContext();
{
                  final SNode tnode26=environment.createOutputNode(myConcepts[10]);
                  try {
                    environment.nodeCopied(context10,tnode26,"tpl/r:3ab3501c-2f4b-48e6-9b6c-e31ff8ef3185/3308300503039581791");
                    SNodeAccessUtil.setProperty(tnode26,myProperties[0],"script");
                    TemplateContext context11=context10.subContext();
{
                      final SNode tnode27=environment.createOutputNode(myConcepts[2]);
                      try {
                        environment.resolveInTemplateLater(tnode27,myAssociationLinks[0],templateNode_rbrxtl_c0a0a1a3a1a1a1a1a1a4a1a9a1a4,"tpl/r:3ab3501c-2f4b-48e6-9b6c-e31ff8ef3185/3308300503039570165","map_Script",context11);
                      }
  finally {
                      }
                      tnode26.addChild(myAggregationLinks[6],tnode27);
                    }
{
                      final SNode tnode28=environment.createOutputNode(myConcepts[11]);
                      try {
                        TemplateContext context12=context11.subContext();
{
                          final SNode tnode29=environment.createOutputNode(myConcepts[12]);
                          try {
                            environment.resolveInTemplateLater(tnode29,myAssociationLinks[1],templateNode_rbrxtl_c0a0a1a1a1a4a1a1a1a1a1a4a1a9a1a4,"tpl/r:3ab3501c-2f4b-48e6-9b6c-e31ff8ef3185/3308300503039570167","map_Script",context12);
                          }
  finally {
                          }
                          tnode28.addChild(myAggregationLinks[7],tnode29);
                        }
                      }
  finally {
                      }
                      tnode26.addChild(myAggregationLinks[8],tnode28);
                    }
                  }
  finally {
                  }
                  tnode25.addChild(myAggregationLinks[9],tnode26);
                }
              }
  finally {
              }
              tnode24.addChild(myAggregationLinks[5],tnode25);
            }
{
              final SNode tnode30=environment.createOutputNode(myConcepts[13]);
              try {
                TemplateContext context13=context9.subContext();
{
                  final SNode tnode31=environment.createOutputNode(myConcepts[14]);
                  try {
                    TemplateContext context14=context13.subContext();
{
                      final SNode tnode32=environment.createOutputNode(myConcepts[15]);
                      try {
                        environment.resolveInTemplateLater(tnode32,myAssociationLinks[2],templateNode_rbrxtl_c0a0a1a1a1a1a1a2a1a4a1a9a1a4,"tpl/r:3ab3501c-2f4b-48e6-9b6c-e31ff8ef3185/3308300503039581791","script",context14);
                      }
  finally {
                      }
                      tnode31.addChild(myAggregationLinks[10],tnode32);
                    }
{
                      final SNode tnode33=environment.createOutputNode(myConcepts[16]);
                      try {
                        tnode33.setReference(myAssociationLinks[1],SReference.create(myAssociationLinks[1],tnode33,PersistenceFacade.getInstance().createModelReference("r:f5e9b11f-5073-4786-8ed1-a9e42307c3f8(JavaKaja.runtime)"),PersistenceFacade.getInstance().createNodeId("3308300503039555813")));
                      }
  finally {
                      }
                      tnode31.addChild(myAggregationLinks[11],tnode33);
                    }
                  }
  finally {
                  }
                  tnode30.addChild(myAggregationLinks[12],tnode31);
                }
              }
  finally {
              }
              tnode24.addChild(myAggregationLinks[5],tnode30);
            }
{
              final SNode tnode34=environment.createOutputNode(myConcepts[13]);
              try {
                TemplateContext context15=context9.subContext();
{
                  final SNode tnode35=environment.createOutputNode(myConcepts[14]);
                  try {
                    TemplateContext context16=context15.subContext();
{
                      final SNode tnode36=environment.createOutputNode(myConcepts[15]);
                      try {
                        environment.resolveInTemplateLater(tnode36,myAssociationLinks[2],templateNode_rbrxtl_c0a0a1a1a1a1a1a3a1a4a1a9a1a4,"tpl/r:3ab3501c-2f4b-48e6-9b6c-e31ff8ef3185/3308300503039581791","script",context16);
                      }
  finally {
                      }
                      tnode35.addChild(myAggregationLinks[10],tnode36);
                    }
{
                      final SNode tnode37=environment.createOutputNode(myConcepts[16]);
                      try {
                        tnode37.setReference(myAssociationLinks[1],SReference.create(myAssociationLinks[1],tnode37,PersistenceFacade.getInstance().createModelReference("r:f5e9b11f-5073-4786-8ed1-a9e42307c3f8(JavaKaja.runtime)"),PersistenceFacade.getInstance().createNodeId("3308300503039569042")));
                      }
  finally {
                      }
                      tnode35.addChild(myAggregationLinks[11],tnode37);
                    }
                  }
  finally {
                  }
                  tnode34.addChild(myAggregationLinks[12],tnode35);
                }
              }
  finally {
              }
              tnode24.addChild(myAggregationLinks[5],tnode34);
            }
          }
  finally {
          }
          tnode21.addChild(myAggregationLinks[3],tnode24);
        }
{
          final SNode tnode38=environment.createOutputNode(myConcepts[17]);
          try {
            SNodeAccessUtil.setProperty(tnode38,myProperties[0],"args");
            TemplateContext context17=context8.subContext();
{
              final SNode tnode39=environment.createOutputNode(myConcepts[18]);
              try {
                TemplateContext context18=context17.subContext();
{
                  final SNode tnode40=environment.createOutputNode(myConcepts[19]);
                  try {
                  }
  finally {
                  }
                  tnode39.addChild(myAggregationLinks[13],tnode40);
                }
              }
  finally {
              }
              tnode38.addChild(myAggregationLinks[6],tnode39);
            }
          }
  finally {
          }
          tnode21.addChild(myAggregationLinks[14],tnode38);
        }
      }
  finally {
      }
      tnode1.addChild(myAggregationLinks[4],tnode21);
    }
  }
  finally {
  }
  return TemplateUtil.singletonList(tnode1);
}
