private Class getDataType(int registrationNo){
  Class clazz=registrations.get(registrationNo);
  Preconditions.checkArgument(clazz != null,"Encountered missing datatype registration for number: %s",registrationNo);
  return clazz;
}
