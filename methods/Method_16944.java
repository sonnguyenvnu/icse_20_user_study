@Override protected void execute(){
  context.nodeSubtype.addMethod(context.constructorDefault.build()).addMethod(context.constructorByKey.build()).addMethod(context.constructorByKeyRef.build());
}
