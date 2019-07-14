private boolean isHasNotAnyChanges(){
  return initialValues == null || initialValues.equals(getCurrentValues());
}
