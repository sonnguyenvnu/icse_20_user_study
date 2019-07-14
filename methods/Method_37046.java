protected Map<String,ComponentInfo> parseComponentInfo(JSONObject cardJson){
  if (cardJson == null || !cardJson.containsKey(COMPONENTINFO)) {
    return null;
  }
  JSONArray componentInfoArray=cardJson.getJSONArray(COMPONENTINFO);
  if (componentInfoArray == null) {
    return null;
  }
  Map<String,ComponentInfo> componentInfoMap=new HashMap<>(128);
  for (int i=0; i < componentInfoArray.size(); i++) {
    JSONObject json=componentInfoArray.getJSONObject(i);
    ComponentInfo info=new ComponentInfo(json);
    mvHelper.renderManager().putComponentInfo(info);
    componentInfoMap.put(info.getId(),info);
  }
  return componentInfoMap;
}
