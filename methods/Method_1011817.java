public Collection<IntentionExecutable> instances(final SNode node,final EditorContext context){
  if (myCachedExecutable == null) {
    myCachedExecutable=Collections.<IntentionExecutable>singletonList(new AttachComment2_Intention.IntentionImplementation());
  }
  return myCachedExecutable;
}
