private void addFactories(){
  context.nodeSubtype.addMethod(newNode(keySpec,keyRefQueueSpec).addStatement("return new $N<>(key, keyReferenceQueue, value, " + "valueReferenceQueue, weight, now)",context.className).build());
  context.nodeSubtype.addMethod(newNode(keyRefSpec).addStatement("return new $N<>(keyReference, value, valueReferenceQueue, weight, now)",context.className).build());
}
