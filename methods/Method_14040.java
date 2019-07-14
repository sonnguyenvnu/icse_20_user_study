@Override public JavaType typeFromId(DatabindContext context,String id) throws IOException {
  return factory.constructSimpleType(ReconConfig.getClassFromMode(id),new JavaType[0]);
}
