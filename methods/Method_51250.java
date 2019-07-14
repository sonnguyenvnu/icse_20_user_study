@Override public void definePropertyDescriptor(PropertyDescriptor<?> propertyDescriptor) throws IllegalArgumentException {
  super.definePropertyDescriptor(propertyDescriptor);
  if (propertyDescriptors == null) {
    propertyDescriptors=new ArrayList<>();
  }
  propertyDescriptors.add(propertyDescriptor);
}
