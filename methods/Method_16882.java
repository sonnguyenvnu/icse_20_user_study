private void addIsRecording(){
  context.cache.addMethod(MethodSpec.methodBuilder("isRecordingStats").addModifiers(context.publicFinalModifiers()).addStatement("return true").returns(boolean.class).build());
}
