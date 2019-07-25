public XmlSignature add(SNode element){
  if (element == null || hasErrors) {
    return this;
  }
  if (SNodeOperations.isInstanceOf(element,MetaAdapterFactory.getConcept(0x479c7a8c02f943b5L,0x9139d910cb22f298L,0x5c842a42c54b10b2L,"jetbrains.mps.core.xml.structure.XmlElement"))) {
    addElement(SNodeOperations.cast(element,MetaAdapterFactory.getConcept(0x479c7a8c02f943b5L,0x9139d910cb22f298L,0x5c842a42c54b10b2L,"jetbrains.mps.core.xml.structure.XmlElement")));
  }
 else   if (SNodeOperations.isInstanceOf(element,MetaAdapterFactory.getConcept(0x698a8d22a10447a0L,0xba8d10e3ec237f13L,0x5c3f3e2c1cef4c1fL,"jetbrains.mps.build.workflow.structure.BwfPathReference"))) {
    sb.append("<pathref ");
    sb.append(BwfPathDeclaration__BehaviorDescriptor.getPathId_id5KZfyKsWnkn.invoke(SLinkOperations.getTarget(SNodeOperations.cast(element,MetaAdapterFactory.getConcept(0x698a8d22a10447a0L,0xba8d10e3ec237f13L,0x5c3f3e2c1cef4c1fL,"jetbrains.mps.build.workflow.structure.BwfPathReference")),MetaAdapterFactory.getReferenceLink(0x698a8d22a10447a0L,0xba8d10e3ec237f13L,0x5c3f3e2c1cef4c1fL,0x5c3f3e2c1cef4c20L,"target"))));
    sb.append("/>");
  }
 else {
    hasErrors=true;
  }
  return this;
}
