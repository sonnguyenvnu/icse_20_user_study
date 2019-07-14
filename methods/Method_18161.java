static YogaNode createYogaNode(ComponentContext componentContext){
  return componentContext.mYogaNodeFactory != null ? componentContext.mYogaNodeFactory.create() : NodeConfig.createYogaNode();
}
