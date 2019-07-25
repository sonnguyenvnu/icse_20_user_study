public byte[] transform(byte[] byteCode) throws Exception {
  CtClass cc=classPool.makeClass(new ByteArrayInputStream(byteCode),false);
  try {
    String initFieldName=INIT_FIELD_PREFIX + generateRandomString();
    addStaticInitStateField(cc,initFieldName);
    String initCode=getInitCall(cc,initFieldName);
    addInitCallToMethods(cc,initFieldName,initCode);
    return cc.toBytecode();
  }
  finally {
    cc.detach();
  }
}
