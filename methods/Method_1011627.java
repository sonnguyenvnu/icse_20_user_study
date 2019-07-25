public void execute(SNode node){
  SNode moduleXml=SNodeOperations.as(node,MetaAdapterFactory.getConcept(0xcf935df46994e9cL,0xa132fa109541cba3L,0x6a3e160a3efe6274L,"jetbrains.mps.build.mps.structure.BuildMpsLayout_ModuleXml"));
  SPropertyOperations.assign(SLinkOperations.addNewChild(moduleXml,MetaAdapterFactory.getContainmentLink(0xcf935df46994e9cL,0xa132fa109541cba3L,0x6a3e160a3efe6274L,0x75cd89729fd8ef2bL,"classpathEntries"),null),MetaAdapterFactory.getProperty(0xcf935df46994e9cL,0xa132fa109541cba3L,0xd94b027412f0824L,0xd94b027412f0827L,"path"),".");
}
