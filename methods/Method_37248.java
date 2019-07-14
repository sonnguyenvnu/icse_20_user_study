protected boolean hasIndexProperty(final BeanProperty bp){
  if (bp.bean == null) {
    return false;
  }
  String indexString=extractIndex(bp);
  if (indexString == null) {
    return hasSimpleProperty(bp);
  }
  Object resultBean=getSimpleProperty(bp);
  if (resultBean == null) {
    return false;
  }
  if (resultBean.getClass().isArray()) {
    int index=parseInt(indexString,bp);
    return (index >= 0) && (index < Array.getLength(resultBean));
  }
  if (resultBean instanceof List) {
    int index=parseInt(indexString,bp);
    return (index >= 0) && (index < ((List)resultBean).size());
  }
  if (resultBean instanceof Map) {
    return ((Map)resultBean).containsKey(indexString);
  }
  return false;
}
