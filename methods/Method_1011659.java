public Collection<IntentionExecutable> instances(final SNode node,final EditorContext context){
  if (myCachedExecutable == null) {
    myCachedExecutable=Collections.<IntentionExecutable>singletonList(new Switch_HasAttributes_Intention.IntentionImplementation());
  }
  return myCachedExecutable;
}
