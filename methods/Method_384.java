public static final int writeJSONString(OutputStream os,Charset charset,Object object,SerializerFeature... features) throws IOException {
  return writeJSONString(os,charset,object,SerializeConfig.globalInstance,null,null,DEFAULT_GENERATE_FEATURE,features);
}
