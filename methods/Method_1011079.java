public Collection<IntentionExecutable> instances(final SNode node,final EditorContext context){
  if (myCachedExecutable == null) {
    myCachedExecutable=Collections.<IntentionExecutable>singletonList(new replace_invokeOperation_with_compactInvoke_Intention.IntentionImplementation());
  }
  return myCachedExecutable;
}
