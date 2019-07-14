private void traverseForText(long timeUs,boolean descendsPNode,String inheritedRegion,Map<String,SpannableStringBuilder> regionOutputs){
  nodeStartsByRegion.clear();
  nodeEndsByRegion.clear();
  if (TAG_METADATA.equals(tag)) {
    return;
  }
  String resolvedRegionId=ANONYMOUS_REGION_ID.equals(regionId) ? inheritedRegion : regionId;
  if (isTextNode && descendsPNode) {
    getRegionOutput(resolvedRegionId,regionOutputs).append(text);
  }
 else   if (TAG_BR.equals(tag) && descendsPNode) {
    getRegionOutput(resolvedRegionId,regionOutputs).append('\n');
  }
 else   if (isActive(timeUs)) {
    for (    Entry<String,SpannableStringBuilder> entry : regionOutputs.entrySet()) {
      nodeStartsByRegion.put(entry.getKey(),entry.getValue().length());
    }
    boolean isPNode=TAG_P.equals(tag);
    for (int i=0; i < getChildCount(); i++) {
      getChild(i).traverseForText(timeUs,descendsPNode || isPNode,resolvedRegionId,regionOutputs);
    }
    if (isPNode) {
      TtmlRenderUtil.endParagraph(getRegionOutput(resolvedRegionId,regionOutputs));
    }
    for (    Entry<String,SpannableStringBuilder> entry : regionOutputs.entrySet()) {
      nodeEndsByRegion.put(entry.getKey(),entry.getValue().length());
    }
  }
}
