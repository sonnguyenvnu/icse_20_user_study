public void execute(SNode node){
  assert !(SPropertyOperations.getBoolean(SLinkOperations.getTarget(((SNode)MakeConceptAbstract_QuickFix.this.getField("conceptBehavior")[0]),MetaAdapterFactory.getReferenceLink(0xaf65afd8f0dd4942L,0x87d963a55f2a9db1L,0x11d43447b1aL,0x11d43447b1fL,"concept")),MetaAdapterFactory.getProperty(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0x1103553c5ffL,0x403a32c5772c7ec2L,"abstract")));
  SPropertyOperations.set(SLinkOperations.getTarget(((SNode)MakeConceptAbstract_QuickFix.this.getField("conceptBehavior")[0]),MetaAdapterFactory.getReferenceLink(0xaf65afd8f0dd4942L,0x87d963a55f2a9db1L,0x11d43447b1aL,0x11d43447b1fL,"concept")),MetaAdapterFactory.getProperty(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0x1103553c5ffL,0x403a32c5772c7ec2L,"abstract"),true);
}
