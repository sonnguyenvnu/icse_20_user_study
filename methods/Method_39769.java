/** 
 * Creates advice's class reader.
 */
private ClassReader createAdviceClassReader(final Class<? extends ProxyAdvice> advice){
  InputStream inputStream=null;
  try {
    inputStream=ClassLoaderUtil.getClassAsStream(advice);
    return new ClassReader(inputStream);
  }
 catch (  IOException ioex) {
    throw new ProxettaException(ioex);
  }
 finally {
    StreamUtil.close(inputStream);
  }
}
