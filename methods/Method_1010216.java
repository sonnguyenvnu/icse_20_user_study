private void reload(){
  final SModel oldModel=getCurrentModelInternal();
  if (oldModel == null) {
    return;
  }
  replace(createModel());
}
