private static void setRendererEncoding(Object renderer,String encoding) throws IllegalAccessException, InvocationTargetException {
  try {
    PropertyDescriptor encodingProperty=new PropertyDescriptor("encoding",renderer.getClass());
    Method method=encodingProperty.getWriteMethod();
    if (method != null) {
      method.invoke(renderer,encoding);
    }
  }
 catch (  IntrospectionException ignored) {
  }
}
