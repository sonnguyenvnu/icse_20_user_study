@Override public Object intercept(final ActionRequest actionRequest) throws Exception {
  Object action=actionRequest.getAction();
  Class actionType=action.getClass();
  PropertyDescriptor[] allProperties=lookupAnnotatedProperties(actionType);
  for (  PropertyDescriptor propertyDescriptor : allProperties) {
    onAnnotatedProperty(actionRequest,propertyDescriptor);
  }
  return actionRequest.invoke();
}
