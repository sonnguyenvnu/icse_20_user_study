public Collection<IntentionExecutable> instances(final SNode node,final EditorContext context){
  if (myCachedExecutable == null) {
    myCachedExecutable=Collections.<IntentionExecutable>singletonList(new CreatePropertyAntiquotation_Intention.IntentionImplementation());
  }
  return myCachedExecutable;
}
