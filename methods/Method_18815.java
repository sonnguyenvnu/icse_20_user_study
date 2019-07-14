public void writeTo(MethodSpec.Builder methodBuilder){
  mEventMethodModels.forEach(e -> writeCase(methodBuilder,e));
  if (mWithErrorPropagation) {
    boolean hasErrorHandler=mEventMethodModels.stream().anyMatch(e -> e.name.toString().equals(INTERNAL_ON_ERROR_HANDLER_NAME));
    if (!hasErrorHandler) {
      writePropagatingErrorCase(methodBuilder);
    }
  }
}
