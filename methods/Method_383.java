/** 
 * @since 1.2.11 
 */
public static final int writeJSONString(OutputStream os,Object object,int defaultFeatures,SerializerFeature... features) throws IOException {
  return writeJSONString(os,IOUtils.UTF8,object,SerializeConfig.globalInstance,null,null,defaultFeatures,features);
}
