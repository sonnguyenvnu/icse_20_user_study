@Override public void definePropertyDescriptor(PropertyDescriptor<?> propertyDescriptor){
  if (getPropertyDescriptor(propertyDescriptor.name()) != null) {
    throw new IllegalArgumentException("There is already a PropertyDescriptor with name '" + propertyDescriptor.name() + "' defined on " + getPropertySourceType() + " " + getName() + ".");
  }
  propertyDescriptors.add(propertyDescriptor);
  Collections.sort(propertyDescriptors);
}
