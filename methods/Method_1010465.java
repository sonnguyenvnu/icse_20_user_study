public void aggregation(SContainmentLinkId link,String name,boolean unordered,int index){
  myActualConcept.addLink(link,name,unordered).setIntIndex(index);
  myAggregations.put(index,MetaAdapterFactory.getContainmentLink(link,name));
  myMetaInfoProvider.setAggregationName(link,name);
}
