public Collection<IntentionExecutable> instances(final SNode node,final EditorContext context){
  if (myCachedExecutable == null) {
    myCachedExecutable=Collections.<IntentionExecutable>singletonList(new SuppressAllTypesystemWarning_Intention.IntentionImplementation());
  }
  return myCachedExecutable;
}
