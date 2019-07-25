public Collection<IntentionExecutable> instances(final SNode node,final EditorContext context){
  if (myCachedExecutable == null) {
    myCachedExecutable=Collections.<IntentionExecutable>singletonList(new add_throws_to_FunctionType_Intention.IntentionImplementation());
  }
  return myCachedExecutable;
}
