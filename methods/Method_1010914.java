public static boolean accept(SNode targetKinds,SNode hasAnnotation){
  if (SNodeOperations.isInstanceOf(targetKinds,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x114a770dc0dL,"jetbrains.mps.baseLanguage.structure.ArrayLiteral"))) {
    for (    SNode expr : SLinkOperations.getChildren(SNodeOperations.cast(targetKinds,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x114a770dc0dL,"jetbrains.mps.baseLanguage.structure.ArrayLiteral")),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x114a770dc0dL,0x114a770fdbfL,"item"))) {
      if (SNodeOperations.isInstanceOf(expr,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xfc37588bc8L,"jetbrains.mps.baseLanguage.structure.EnumConstantReference")) && acceptKind(SNodeOperations.cast(expr,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xfc37588bc8L,"jetbrains.mps.baseLanguage.structure.EnumConstantReference")),hasAnnotation)) {
        return true;
      }
    }
    return false;
  }
 else   if (SNodeOperations.isInstanceOf(targetKinds,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xfc37588bc8L,"jetbrains.mps.baseLanguage.structure.EnumConstantReference"))) {
    return acceptKind(SNodeOperations.cast(targetKinds,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xfc37588bc8L,"jetbrains.mps.baseLanguage.structure.EnumConstantReference")),hasAnnotation);
  }
 else {
    return false;
  }
}
