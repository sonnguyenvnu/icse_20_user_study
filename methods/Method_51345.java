@Override public String dysfunctionReason(){
  for (  PropertyDescriptor<?> descriptor : getOverriddenPropertyDescriptors()) {
    String error=errorForPropCapture(descriptor);
    if (error != null) {
      return error;
    }
  }
  return null;
}
