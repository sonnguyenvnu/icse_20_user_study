private SNode convert(MappingPriorityRule source){
  SNode rule=SModelOperations.createNewNode(myModel,null,MetaAdapterFactory.getConcept(0x86ef829012bb4ca7L,0x947f093788f263a9L,0x5869770da61dfe38L,"jetbrains.mps.lang.project.structure.MappingPriorityRule"));
switch (source.getType()) {
case BEFORE_OR_TOGETHER:
    SPropertyOperations.setEnum(rule,MetaAdapterFactory.getProperty(0x86ef829012bb4ca7L,0x947f093788f263a9L,0x5869770da61dfe38L,0x5869770da61dfe3dL,"type"),0x5869770da61dfe3cL,"before_or_together");
  break;
case STRICTLY_BEFORE:
SPropertyOperations.setEnum(rule,MetaAdapterFactory.getProperty(0x86ef829012bb4ca7L,0x947f093788f263a9L,0x5869770da61dfe38L,0x5869770da61dfe3dL,"type"),0x5869770da61dfe3aL,"strictly_before");
break;
case STRICTLY_AFTER:
SPropertyOperations.setEnum(rule,MetaAdapterFactory.getProperty(0x86ef829012bb4ca7L,0x947f093788f263a9L,0x5869770da61dfe38L,0x5869770da61dfe3dL,"type"),0x24ae9488ebb07a1fL,"strictly_after");
break;
case AFTER_OR_TOGETHER:
SPropertyOperations.setEnum(rule,MetaAdapterFactory.getProperty(0x86ef829012bb4ca7L,0x947f093788f263a9L,0x5869770da61dfe38L,0x5869770da61dfe3dL,"type"),0x24ae9488ebb07a1eL,"after_or_together");
break;
default :
SPropertyOperations.setEnum(rule,MetaAdapterFactory.getProperty(0x86ef829012bb4ca7L,0x947f093788f263a9L,0x5869770da61dfe38L,0x5869770da61dfe3dL,"type"),0x5869770da61dfe3bL,"strictly_together");
}
SLinkOperations.setTarget(rule,MetaAdapterFactory.getContainmentLink(0x86ef829012bb4ca7L,0x947f093788f263a9L,0x5869770da61dfe38L,0x25c3f284595702edL,"left"),convert(source.getLeft()));
SLinkOperations.setTarget(rule,MetaAdapterFactory.getContainmentLink(0x86ef829012bb4ca7L,0x947f093788f263a9L,0x5869770da61dfe38L,0x25c3f284595702eeL,"right"),convert(source.getRight()));
return rule;
}
