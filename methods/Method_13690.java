/** 
 * @return scx endpoint
 */
@ReadOperation public Map<String,Object> invoke(){
  Map<String,Object> scxEndpoint=new HashMap<>();
  LOGGER.info("SCX endpoint invoke, scxProperties is {}",scxProperties);
  scxEndpoint.put("namespace",edasProperties == null ? "" : edasProperties.getNamespace());
  scxEndpoint.put("scxProperties",scxProperties);
  return scxEndpoint;
}
