public static YogaNode create(){
  return YogaConfig.useBatchingForLayoutOutputs ? new YogaNodeJNIBatching() : new YogaNodeJNI();
}
