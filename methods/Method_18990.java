private static List<RenderInfo> collectComponentInfos(int startIndex,int numItems,SparseArray<RenderInfo> componentInfoSparseArray){
  ArrayList<RenderInfo> renderInfos=new ArrayList<>(numItems);
  for (int i=startIndex; i < startIndex + numItems; i++) {
    RenderInfo renderInfo=componentInfoSparseArray.get(i);
    if (renderInfo == null) {
      throw new IllegalStateException(String.format(Locale.US,"Index %d does not have a corresponding renderInfo",i));
    }
    renderInfos.add(renderInfo);
  }
  return renderInfos;
}
