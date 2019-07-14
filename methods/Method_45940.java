@Override public void decodeObjectByTemplate(AbstractByteBuf data,Map<String,String> context,SofaRequest template) throws SofaRpcException {
  try {
    UnsafeByteArrayInputStream inputStream=new UnsafeByteArrayInputStream(data.array());
    Hessian2Input input=new Hessian2Input(inputStream);
    input.setSerializerFactory(serializerFactory);
    Object object=input.readObject();
    SofaRequest tmp=(SofaRequest)object;
    String targetServiceName=tmp.getTargetServiceUniqueName();
    if (targetServiceName == null) {
      throw buildDeserializeError("Target service name of request is null!");
    }
    template.setMethodName(tmp.getMethodName());
    template.setMethodArgSigs(tmp.getMethodArgSigs());
    template.setTargetServiceUniqueName(tmp.getTargetServiceUniqueName());
    template.setTargetAppName(tmp.getTargetAppName());
    template.addRequestProps(tmp.getRequestProps());
    String interfaceName=ConfigUniqueNameGenerator.getInterfaceName(targetServiceName);
    template.setInterfaceName(interfaceName);
    String[] sig=template.getMethodArgSigs();
    Class<?>[] classSig=ClassTypeUtils.getClasses(sig);
    final Object[] args=new Object[sig.length];
    for (int i=0; i < template.getMethodArgSigs().length; ++i) {
      args[i]=input.readObject(classSig[i]);
    }
    template.setMethodArgs(args);
    input.close();
  }
 catch (  IOException e) {
    throw buildDeserializeError(e.getMessage(),e);
  }
}
