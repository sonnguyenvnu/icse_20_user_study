public Collection<IntentionExecutable> instances(final SNode node,final EditorContext context){
  if (myCachedExecutable == null) {
    myCachedExecutable=Collections.<IntentionExecutable>singletonList(new IntroduceConstant_Intention.IntentionImplementation());
  }
  return myCachedExecutable;
}
