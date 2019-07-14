@Override public JavaType typeFromId(DatabindContext context,String id) throws IOException {
  Class<? extends AbstractOperation> opClass=OperationRegistry.resolveOperationId(id);
  if (opClass == null) {
    opClass=UnknownOperation.class;
  }
  return factory.constructSimpleType(opClass,new JavaType[0]);
}
