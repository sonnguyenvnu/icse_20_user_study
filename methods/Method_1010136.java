public static DynamicReference create(SNode outputNode,SModelReference targetModelRef,DynamicReference prototype){
  DynamicReference outputReference=new DynamicReference(prototype.getLink(),outputNode,targetModelRef,prototype.getResolveInfo());
  outputReference.setOrigin(prototype.getOrigin());
  return outputReference;
}
