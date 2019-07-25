private static void migrate(SModule m){
{
    SearchScope scope_xqhmgi_a0k=CommandUtil.createScope(m);
    final SearchScope scope_xqhmgi_a0k_0=new EditableFilteringScope(scope_xqhmgi_a0k);
    QueryExecutionContext context=new QueryExecutionContext(){
      public SearchScope getDefaultSearchScope(){
        return scope_xqhmgi_a0k_0;
      }
    }
;
    for (    SNode propertyAccess : CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x108f96cca6fL,"jetbrains.mps.lang.smodel.structure.SPropertyAccess"),false))) {
      SNode newProperty=EnumUsagesMigration.migratePropertyReference(propertyAccess,MetaAdapterFactory.getReferenceLink(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x108f96cca6fL,0x108f9727bcdL,"property"));
      if (newProperty != null) {
        migratePropertyAccess(propertyAccess);
      }
    }
    for (    SNode oldEnumMemberNameOp : CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x120c01735d3L,"jetbrains.mps.lang.smodel.structure.EnumMember_NameOperation_Old"),false))) {
      if (ListSequence.fromList(SLinkOperations.getChildren(oldEnumMemberNameOp,MetaAdapterFactory.getContainmentLink(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x10802efe25aL,0x47bf8397520e5942L,"smodelAttribute"))).isNotEmpty()) {
        continue;
      }
      SNode memberExpression=IOperation__BehaviorDescriptor.getOperand_idhEwIP$m.invoke(oldEnumMemberNameOp);
      SNode dotExpression=SNodeOperations.as(SNodeOperations.getParent(oldEnumMemberNameOp),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x116b46a08c4L,"jetbrains.mps.baseLanguage.structure.DotExpression"));
      SNode res=EnumExpressionsMigration.insertMemberNameOp(getEnumForMigratingEnumMemberOp(oldEnumMemberNameOp),dotExpression,memberExpression);
      ListSequence.fromList(SLinkOperations.getChildren(res,MetaAdapterFactory.getContainmentLink(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x10802efe25aL,0x47bf8397520e5942L,"smodelAttribute"))).addSequence(ListSequence.fromList(SLinkOperations.getChildren(dotExpression,MetaAdapterFactory.getContainmentLink(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x10802efe25aL,0x47bf8397520e5942L,"smodelAttribute"))));
    }
    for (    SNode oldEnumMemberValueOp : CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x120bff92dbeL,"jetbrains.mps.lang.smodel.structure.EnumMember_ValueOperation_Old"),false))) {
      if (ListSequence.fromList(SLinkOperations.getChildren(oldEnumMemberValueOp,MetaAdapterFactory.getContainmentLink(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x10802efe25aL,0x47bf8397520e5942L,"smodelAttribute"))).isNotEmpty()) {
        continue;
      }
      SNode memberExpression=IOperation__BehaviorDescriptor.getOperand_idhEwIP$m.invoke(oldEnumMemberValueOp);
      SNode dotExpression=SNodeOperations.as(SNodeOperations.getParent(oldEnumMemberValueOp),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x116b46a08c4L,"jetbrains.mps.baseLanguage.structure.DotExpression"));
      SNode res=EnumExpressionsMigration.insertMemberValueOp(getEnumForMigratingEnumMemberOp(oldEnumMemberValueOp),dotExpression,memberExpression);
      ListSequence.fromList(SLinkOperations.getChildren(res,MetaAdapterFactory.getContainmentLink(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x10802efe25aL,0x47bf8397520e5942L,"smodelAttribute"))).addSequence(ListSequence.fromList(SLinkOperations.getChildren(dotExpression,MetaAdapterFactory.getContainmentLink(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x10802efe25aL,0x47bf8397520e5942L,"smodelAttribute"))));
    }
    for (    SNode enumOpInvocation : CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x120ed32e98bL,"jetbrains.mps.lang.smodel.structure.SEnumOperationInvocation"),false))) {
      SNode operation=SLinkOperations.getTarget(enumOpInvocation,MetaAdapterFactory.getContainmentLink(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x120ed32e98bL,0x120ed35f667L,"operation"));
      if (ListSequence.fromList(SLinkOperations.getChildren(enumOpInvocation,MetaAdapterFactory.getContainmentLink(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x10802efe25aL,0x47bf8397520e5942L,"smodelAttribute"))).isNotEmpty()) {
        continue;
      }
      if (ListSequence.fromList(SLinkOperations.getChildren(operation,MetaAdapterFactory.getContainmentLink(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x10802efe25aL,0x47bf8397520e5942L,"smodelAttribute"))).isNotEmpty()) {
        continue;
      }
      SNode oldEnum=SLinkOperations.getTarget(enumOpInvocation,MetaAdapterFactory.getReferenceLink(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x120ed32e98bL,0x120ed32e98cL,"enumDeclaration"));
      SNode newEnum=SNodeOperations.cast(SNodeOperations.getParent(SNodeOperations.getParent(oldEnum)),MetaAdapterFactory.getConcept(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0x2e770ca32c607c5fL,"jetbrains.mps.lang.structure.structure.EnumerationDeclartaion"));
      if (SNodeOperations.isInstanceOf(operation,MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x120ed37e6b4L,"jetbrains.mps.lang.smodel.structure.SEnum_MembersOperation_Old"))) {
        SNode replacement=SNodeOperations.replaceWithAnother(enumOpInvocation,_quotation_createNode_xqhmgi_a0a0a0k0j0a0k(newEnum));
        continue;
      }
      if (SNodeOperations.isInstanceOf(operation,MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x120ed37e691L,"jetbrains.mps.lang.smodel.structure.SEnum_MemberOperation_Old"))) {
        SNode oldLiteral=SNodeOperations.cast(operation,MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x120ed37e691L,"jetbrains.mps.lang.smodel.structure.SEnum_MemberOperation_Old"));
        SNodeOperations.replaceWithAnother(enumOpInvocation,_quotation_createNode_xqhmgi_a0a1a21a9a0a01(newEnum,EnumerationMemberDeclaration_Old__BehaviorDescriptor.findReplacement_id54m$yuDZW0l.invoke(SLinkOperations.getTarget(oldLiteral,MetaAdapterFactory.getReferenceLink(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x120ed37e691L,0x120ed37e692L,"member")))));
        continue;
      }
      if (SNodeOperations.isInstanceOf(operation,MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x120ed37e64eL,"jetbrains.mps.lang.smodel.structure.SEnum_MemberForValueOperation_Old"))) {
        EnumExpressionsMigration.insertMemberForValueOp(newEnum,enumOpInvocation,SLinkOperations.getTarget(SNodeOperations.cast(operation,MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x120ed37e64eL,"jetbrains.mps.lang.smodel.structure.SEnum_MemberForValueOperation_Old")),MetaAdapterFactory.getContainmentLink(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x120ed37e64eL,0x120ed37e64fL,"valueExpression")));
        continue;
      }
      if (SNodeOperations.isInstanceOf(operation,MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x120ed37e60cL,"jetbrains.mps.lang.smodel.structure.SEnum_MemberForNameOperation_Old"))) {
        EnumExpressionsMigration.insertMemberForNameOp(newEnum,enumOpInvocation,SLinkOperations.getTarget(SNodeOperations.cast(operation,MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x120ed37e60cL,"jetbrains.mps.lang.smodel.structure.SEnum_MemberForNameOperation_Old")),MetaAdapterFactory.getContainmentLink(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x120ed37e60cL,0x120ed37e60dL,"nameExpression")));
        continue;
      }
    }
    for (    SNode enumMemberValueRef : CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x60c7f83bafd83b5bL,"jetbrains.mps.lang.smodel.structure.EnumMemberValueRefExpression"),false))) {
      if (ListSequence.fromList(SLinkOperations.getChildren(enumMemberValueRef,MetaAdapterFactory.getContainmentLink(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x10802efe25aL,0x47bf8397520e5942L,"smodelAttribute"))).isNotEmpty()) {
        continue;
      }
      SNode newMember=EnumerationMemberDeclaration_Old__BehaviorDescriptor.findReplacement_id54m$yuDZW0l.invoke(SLinkOperations.getTarget(enumMemberValueRef,MetaAdapterFactory.getReferenceLink(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x60c7f83bafd83b5bL,0x60c7f83bafda1168L,"member")));
      EnumExpressionsMigration.insertMemberValueOp(SNodeOperations.as(SNodeOperations.getParent(newMember),MetaAdapterFactory.getConcept(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0x2e770ca32c607c5fL,"jetbrains.mps.lang.structure.structure.EnumerationDeclartaion")),enumMemberValueRef,_quotation_createNode_xqhmgi_c0e0l0a0k(SNodeOperations.cast(SNodeOperations.getParent(newMember),MetaAdapterFactory.getConcept(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0x2e770ca32c607c5fL,"jetbrains.mps.lang.structure.structure.EnumerationDeclartaion")),newMember));
    }
    for (    SNode enumMemberType : CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x120bfe51421L,"jetbrains.mps.lang.smodel.structure.SEnumerationMemberType"),false))) {
      EnumUsagesMigration.migrateEnumReference(enumMemberType,MetaAdapterFactory.getReferenceLink(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x120bfe51421L,0x120bff1303bL,"enum"));
    }
  }
}
