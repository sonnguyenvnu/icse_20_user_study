protected void formatId(String ruleId){
  if (StringUtils.isBlank(ruleId)) {
    return;
  }
  String[] ids=ruleId.split(ID_SPLIT);
  List<String> effectiveId=new ArrayList<String>(ids.length);
  List<String> excludeId=new ArrayList<String>(ids.length);
  for (  String id : ids) {
    if (id.startsWith(ID_EXCLUDE)) {
      excludeId.add(id.substring(1));
    }
 else {
      effectiveId.add(id);
    }
  }
  this.effectiveId=effectiveId;
  this.excludeId=excludeId;
  this.allEffective=false;
}
