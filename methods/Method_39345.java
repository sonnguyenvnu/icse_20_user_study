@SuppressWarnings({"unchecked"}) protected Class<T> resolveSetType(final PropertyDescriptor propertyDescriptor){
  Class<T> type=(Class<T>)propertyDescriptor.getType();
  if (ClassUtil.isTypeOf(type,Collection.class)) {
    return type;
  }
  throw new PetiteException("Unsupported Petite set type: " + type.getName());
}
