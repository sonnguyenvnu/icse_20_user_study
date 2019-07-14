private void writePropagatingErrorCase(MethodSpec.Builder methodBuilder){
  methodBuilder.beginControlFlow("case $L:",INTERNAL_ON_ERROR_HANDLER_NAME.toString().hashCode()).addStatement("dispatchErrorEvent(($L) eventHandler.params[0], ($L) eventState)",mContextClass,ClassNames.ERROR_EVENT).addStatement("return null").endControlFlow();
}
