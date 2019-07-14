private void addQueueFlag(){
  context.nodeSubtype.addField(int.class,"queueType");
  context.nodeSubtype.addMethod(MethodSpec.methodBuilder("getQueueType").addModifiers(context.publicFinalModifiers()).returns(int.class).addStatement("return queueType").build());
  context.nodeSubtype.addMethod(MethodSpec.methodBuilder("setQueueType").addModifiers(context.publicFinalModifiers()).addParameter(int.class,"queueType").addStatement("this.queueType = queueType").build());
}
