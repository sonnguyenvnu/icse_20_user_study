@SneakyThrows protected <T>Object getExistingDataId(String formId,T data){
  RDBTable<T> table=getTable(formId);
  String triggerName="check-data-existing";
  boolean useTrigger=table.getMeta().triggerIsSupport(triggerName);
  String idProperty=getIdProperty(table.getMeta());
  if (useTrigger) {
    Map<String,Object> triggerContext=new HashMap<>();
    triggerContext.put("table",table);
    triggerContext.put("database",getDatabase(formId));
    triggerContext.put("data",data);
    Object result=table.getMeta().on(triggerName,triggerContext);
    if (result instanceof String) {
      return result;
    }
    if (result instanceof Map) {
      Object id=((Map)result).get(idProperty);
      if (id == null) {
        log.error("table:[{}]????????:[{}],?????????:[{}]",table.getMeta().getName(),data,idProperty);
        throw new BusinessException("????,??????");
      }
      return id;
    }
  }
 else {
    Map<String,Object> mapData=FastBeanCopier.copy(data,new HashMap<>());
    Object id=mapData.get(idProperty);
    if (null == id) {
      return null;
    }
    Object existing=selectSingle(formId,QueryParamEntity.single(idProperty,id).includes(idProperty));
    if (null != existing) {
      mapData=FastBeanCopier.copy(data,new HashMap<>());
      return mapData.get(idProperty);
    }
  }
  return null;
}
