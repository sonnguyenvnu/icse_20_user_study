/** 
 * @param attribute attribute containing commented node. This node must have parent
 * @throws IllegalArgumentException if attribute has no parent   
 */
public static SNode uncomment(@NotNull SNode attribute){
  SNode parent=SNodeOperations.getParent(attribute);
  if (parent == null) {
    throw new IllegalArgumentException("Node to uncomment has no parent. Node: " + ((String)BHReflection.invoke0(attribute,MetaAdapterFactory.getConcept(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x10802efe25aL,"jetbrains.mps.lang.core.structure.BaseConcept"),SMethodTrimmedId.create("getPresentation",null,"hEwIMiw"))) + " Node id: " + attribute.getNodeId());
  }
  return new NodeUncommenter(attribute).uncomment();
}
