public static YogaNode create(YogaConfig config){
  return YogaConfig.useBatchingForLayoutOutputs ? new YogaNodeJNIBatching(config) : new YogaNodeJNI(config);
}
