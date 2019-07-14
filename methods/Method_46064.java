/** 
 * Create provider id
 * @param model RpcServerLookoutModel
 * @return Id
 */
public Id createMethodProviderId(RpcServerLookoutModel model){
  Map<String,String> tags=new HashMap<String,String>(5);
  tags.put("app",StringUtils.defaultString(model.getApp()));
  tags.put("service",StringUtils.defaultString(model.getService()));
  tags.put("method",StringUtils.defaultString(model.getMethod()));
  tags.put("protocol",StringUtils.defaultString(model.getProtocol()));
  tags.put("caller_app",StringUtils.defaultString(model.getCallerApp()));
  return rpcLookoutId.fetchProviderStatId().withTags(tags);
}
