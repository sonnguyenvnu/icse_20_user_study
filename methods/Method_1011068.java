public Collection<IntentionExecutable> instances(final SNode node,final EditorContext context){
  if (myCachedExecutable == null) {
    myCachedExecutable=Collections.<IntentionExecutable>singletonList(new add_parameter_to_InternalClassifierType_Intention.IntentionImplementation());
  }
  return myCachedExecutable;
}
