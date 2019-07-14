public static void addSerializer(Class clazz,CustomHessianSerializer serializerManager){
  CUSTOM_SERIALIZERS.put(clazz,serializerManager);
}
