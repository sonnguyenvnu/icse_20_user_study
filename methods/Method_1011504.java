public void execute(SNode node){
  if (((SNode)CorrectDuplicateId_QuickFix.this.getField("c")[0]) != null) {
    SPropertyOperations.assign(((SNode)CorrectDuplicateId_QuickFix.this.getField("c")[0]),MetaAdapterFactory.getProperty(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0x1103553c5ffL,0x5d2e6079771f8cc0L,"conceptId"),ConceptIdHelper.generateConceptId(SNodeOperations.getModel(((SNode)CorrectDuplicateId_QuickFix.this.getField("c")[0])),((SNode)CorrectDuplicateId_QuickFix.this.getField("c")[0])) + "");
  }
  if (((SNode)CorrectDuplicateId_QuickFix.this.getField("p")[0]) != null) {
    SPropertyOperations.assign(((SNode)CorrectDuplicateId_QuickFix.this.getField("p")[0]),MetaAdapterFactory.getProperty(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0xf979bd086bL,0x35a81382d82a4d9L,"propertyId"),ConceptIdHelper.generatePropertyId(((SNode)CorrectDuplicateId_QuickFix.this.getField("c")[0]),((SNode)CorrectDuplicateId_QuickFix.this.getField("p")[0])) + "");
  }
  if (((SNode)CorrectDuplicateId_QuickFix.this.getField("l")[0]) != null) {
    SPropertyOperations.assign(((SNode)CorrectDuplicateId_QuickFix.this.getField("l")[0]),MetaAdapterFactory.getProperty(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0xf979bd086aL,0x35a81382d82a4e4L,"linkId"),ConceptIdHelper.generateLinkId(((SNode)CorrectDuplicateId_QuickFix.this.getField("c")[0]),((SNode)CorrectDuplicateId_QuickFix.this.getField("l")[0])) + "");
  }
  if (((SNode)CorrectDuplicateId_QuickFix.this.getField("d")[0]) != null) {
    SPropertyOperations.assign(((SNode)CorrectDuplicateId_QuickFix.this.getField("d")[0]),MetaAdapterFactory.getProperty(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0xfc26875dfaL,0x6c1f946a87044403L,"datatypeId"),ConceptIdHelper.generateDatatypeId(SNodeOperations.getModel(((SNode)CorrectDuplicateId_QuickFix.this.getField("d")[0])),((SNode)CorrectDuplicateId_QuickFix.this.getField("d")[0])) + "");
  }
  if (((SNode)CorrectDuplicateId_QuickFix.this.getField("m")[0]) != null) {
    SPropertyOperations.assign(((SNode)CorrectDuplicateId_QuickFix.this.getField("m")[0]),MetaAdapterFactory.getProperty(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0x2e770ca32c607c60L,0x13b8f6fdce540e38L,"memberId"),ConceptIdHelper.generateEnumMemberId(SNodeOperations.as(((SNode)CorrectDuplicateId_QuickFix.this.getField("d")[0]),MetaAdapterFactory.getConcept(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0x2e770ca32c607c5fL,"jetbrains.mps.lang.structure.structure.EnumerationDeclartaion")),((SNode)CorrectDuplicateId_QuickFix.this.getField("m")[0])) + "");
  }
}
