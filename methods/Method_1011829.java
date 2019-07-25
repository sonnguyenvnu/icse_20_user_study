public void execute(SNode node){
  SNode prev=SNodeOperations.cast(SNodeOperations.getPrevSibling(node),MetaAdapterFactory.getConcept(0xa7d67633e8d9473bL,0x98ce995a7aa66941L,0x4f786d85fe288176L,"jetbrains.mps.samples.heating.structure.Slot"));
  if ((prev != null)) {
    SPropertyOperations.assign(SNodeOperations.cast(node,MetaAdapterFactory.getConcept(0xa7d67633e8d9473bL,0x98ce995a7aa66941L,0x4f786d85fe288176L,"jetbrains.mps.samples.heating.structure.Slot")),MetaAdapterFactory.getProperty(0xa7d67633e8d9473bL,0x98ce995a7aa66941L,0x4f786d85fe288176L,0x4f786d85fe28827cL,"start"),SPropertyOperations.getInteger(prev,MetaAdapterFactory.getProperty(0xa7d67633e8d9473bL,0x98ce995a7aa66941L,0x4f786d85fe288176L,0x4f786d85fe28827cL,"start")) + 1);
  }
 else {
    SPropertyOperations.assign(SNodeOperations.cast(node,MetaAdapterFactory.getConcept(0xa7d67633e8d9473bL,0x98ce995a7aa66941L,0x4f786d85fe288176L,"jetbrains.mps.samples.heating.structure.Slot")),MetaAdapterFactory.getProperty(0xa7d67633e8d9473bL,0x98ce995a7aa66941L,0x4f786d85fe288176L,0x4f786d85fe28827cL,"start"),0);
  }
}
