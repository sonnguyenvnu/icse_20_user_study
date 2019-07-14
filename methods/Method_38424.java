protected void onIdAttrEnd(final Tag tag){
  decoraTagEnd=tag.getTagPosition() + tag.getTagLength();
  decoraTagDefaultValueEnd=tag.getTagPosition();
  defineDecoraTag();
}
