private void traverseForImage(long timeUs,String inheritedRegion,List<Pair<String,String>> regionImageList){
  String resolvedRegionId=ANONYMOUS_REGION_ID.equals(regionId) ? inheritedRegion : regionId;
  if (isActive(timeUs) && TAG_DIV.equals(tag) && imageId != null) {
    regionImageList.add(new Pair<>(resolvedRegionId,imageId));
    return;
  }
  for (int i=0; i < getChildCount(); ++i) {
    getChild(i).traverseForImage(timeUs,resolvedRegionId,regionImageList);
  }
}
