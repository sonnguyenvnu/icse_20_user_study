public void putComponentInfo(ComponentInfo info){
  renderServiceMap.get(info.getType()).onParseComponentInfo(info);
  componentInfoMap.put(info.getId(),info);
}
