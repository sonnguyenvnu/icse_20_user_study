static TypeSpecDataHolder generateOnStateUpdateMethods(SpecModel specModel){
  TypeSpecDataHolder.Builder dataHolder=TypeSpecDataHolder.newBuilder();
  int index=0;
  for (  SpecMethodModel<UpdateStateMethod,Void> updateStateMethod : specModel.getUpdateStateMethods()) {
    dataHolder.addTypeSpecDataHolder(generateOnStateUpdateMethod(specModel,updateStateMethod,StateUpdateType.DEFAULT,index));
    dataHolder.addTypeSpecDataHolder(generateOnStateUpdateMethod(specModel,updateStateMethod,StateUpdateType.ASYNC,index));
    dataHolder.addTypeSpecDataHolder(generateOnStateUpdateMethod(specModel,updateStateMethod,StateUpdateType.SYNC,index));
    index++;
  }
  if (hasUpdateStateWithTransition(specModel)) {
    for (    SpecMethodModel<UpdateStateMethod,Void> updateStateWithTransitionMethod : specModel.getUpdateStateWithTransitionMethods()) {
      dataHolder.addTypeSpecDataHolder(generateOnStateUpdateMethod(specModel,updateStateWithTransitionMethod,StateUpdateType.WITH_TRANSITION,index));
      index++;
    }
  }
  return dataHolder.build();
}
