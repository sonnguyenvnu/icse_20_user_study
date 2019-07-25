public NettyInvocationBuilder request(){
  String resource=PATH_SEPARATOR + StringUtils.join(path,PATH_SEPARATOR);
  List<String> params=new ArrayList<>();
  for (  Map.Entry<String,String> entry : queryParams.entrySet()) {
    params.add(entry.getKey() + "=" + encodeComponent(entry.getValue(),HttpConstants.DEFAULT_CHARSET));
  }
  for (  Map.Entry<String,Set<String>> entry : queryParamsSet.entrySet()) {
    for (    String entryValueValue : entry.getValue()) {
      params.add(entry.getKey() + "=" + encodeComponent(entryValueValue,HttpConstants.DEFAULT_CHARSET));
    }
  }
  if (!params.isEmpty()) {
    resource=resource + "?" + StringUtils.join(params,"&");
  }
  return new NettyInvocationBuilder(channelProvider,resource).header("Host",host);
}
