public static SNodeReference deserialize(@NotNull String from){
  int delimiterIndex=from.lastIndexOf('/');
  if (delimiterIndex < 0) {
    throw new IncorrectNodeIdFormatException("No delimiter discovered in the passed argument " + from);
  }
  String nodeId=StringUtil.unescapeRefChars(from.substring(delimiterIndex + 1));
  SNodeId sNodeId;
  if (String.valueOf((Object)null).equals(nodeId)) {
    sNodeId=null;
  }
 else {
    sNodeId=PersistenceFacade.getInstance().createNodeId(nodeId);
  }
  String modelReference=from.substring(0,delimiterIndex);
  SModelReference sModelReference;
  if (String.valueOf((Object)null).equals(modelReference)) {
    sModelReference=null;
  }
 else {
    sModelReference=PersistenceFacade.getInstance().createModelReference(modelReference);
  }
  return new jetbrains.mps.smodel.SNodePointer(sModelReference,sNodeId);
}
