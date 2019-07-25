public ProcessHandlerBuilder append(@Nullable File file){
  if (file != null) {
    ListSequence.fromList(myCommandLine).addElement(file.getAbsolutePath());
  }
  return this;
}
