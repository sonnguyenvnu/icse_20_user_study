/** 
 * Return a  {@link String} representation of the signature. The difference to {@link #toString()} is thatthis method always returns stable result across different Map capacity and Java versions, ideal for storing and caching. This is done by visiting the Map  {@link Map}s by the order of its keys. This method is more costly tha toString(). If there is no requirement for stability, prefer to use toString().
 */
public String dump(){
  final ProtocolVersion protocolVersion=AllProtocolVersions.LATEST_PROTOCOL_VERSION;
  final DataMap pathKeysMap=new DataMap(URIParamUtils.encodePathKeysForUri(_pathKeys,protocolVersion));
  final DataMap queryParamsMap=QueryParamsUtil.convertToDataMap(_queryParams,_queryParamClasses,protocolVersion);
  final ToStringBuilder builder=new ToStringBuilder(null,ToStringStyle.SHORT_PREFIX_STYLE).append("baseUriTemplate",_baseUriTemplate).append("pathKeys",Data.dump("",pathKeysMap,"")).append("id",_id).append("queryParams",Data.dump("",queryParamsMap,""));
  return builder.toString();
}
