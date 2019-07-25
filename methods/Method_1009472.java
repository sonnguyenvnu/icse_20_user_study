public Object get(Class<?> tClass){
  if (data != null)   return data;
  return serializer.toObject(source.createByteArray(),tClass);
}
