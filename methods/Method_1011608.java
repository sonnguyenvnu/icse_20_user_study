public void execute(SNode node){
  SNode containerName=SNodeOperations.cast(node,MetaAdapterFactory.getConcept(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x3cca41cd0fe51d4fL,"jetbrains.mps.build.structure.BuildString"));
  SNode last=ListSequence.fromList(SLinkOperations.getChildren(containerName,MetaAdapterFactory.getContainmentLink(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x3cca41cd0fe51d4fL,0x440d7ea3b68cba4bL,"parts"))).last();
  if (SNodeOperations.isInstanceOf(last,MetaAdapterFactory.getConcept(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x440d7ea3b68b7d03L,"jetbrains.mps.build.structure.BuildTextStringPart"))) {
    SNode text=SNodeOperations.cast(last,MetaAdapterFactory.getConcept(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x440d7ea3b68b7d03L,"jetbrains.mps.build.structure.BuildTextStringPart"));
    int dot=SPropertyOperations.getString(text,MetaAdapterFactory.getProperty(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x440d7ea3b68b7d03L,0x440d7ea3b68c4d56L,"text")).indexOf('.');
    SPropertyOperations.assign(text,MetaAdapterFactory.getProperty(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x440d7ea3b68b7d03L,0x440d7ea3b68c4d56L,"text"),((dot >= 0 ? SPropertyOperations.getString(text,MetaAdapterFactory.getProperty(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x440d7ea3b68b7d03L,0x440d7ea3b68c4d56L,"text")).substring(0,dot) : SPropertyOperations.getString(text,MetaAdapterFactory.getProperty(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x440d7ea3b68b7d03L,0x440d7ea3b68c4d56L,"text")))) + ((String)fixContainerName_QuickFix.this.getField("newExtension")[0]));
  }
 else {
    ListSequence.fromList(SLinkOperations.getChildren(containerName,MetaAdapterFactory.getContainmentLink(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x3cca41cd0fe51d4fL,0x440d7ea3b68cba4bL,"parts"))).addElement(createBuildTextStringPart_uzundk_a0a0a0c0c(((String)fixContainerName_QuickFix.this.getField("newExtension")[0])));
  }
}
