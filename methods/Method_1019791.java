public void populate(IContext context,Object pojo) throws Exception {
  PropertyDescriptor[] descriptors=getPropertyDescriptors(pojo);
  for (int i=0; i < descriptors.length; i++) {
    PropertyDescriptor descriptor=descriptors[i];
    try {
      Object value=getSimpleProperty(pojo,descriptor);
      context.put(descriptor.getName(),value);
    }
 catch (    Throwable e) {
      e.printStackTrace();
    }
  }
}
