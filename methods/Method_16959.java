private void addKeyMethods(){
  nodeFactory.addMethod(newNodeMethod(keySpec,keyRefQueueSpec)).addMethod(newNodeMethod(keyRefSpec)).addMethod(newReferenceKeyMethod()).addMethod(newLookupKeyMethod());
}
