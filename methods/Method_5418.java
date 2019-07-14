private static List<HlsUrl> copyRenditionsList(List<HlsUrl> renditions,int groupIndex,List<StreamKey> streamKeys){
  List<HlsUrl> copiedRenditions=new ArrayList<>(streamKeys.size());
  for (int i=0; i < renditions.size(); i++) {
    HlsUrl rendition=renditions.get(i);
    for (int j=0; j < streamKeys.size(); j++) {
      StreamKey streamKey=streamKeys.get(j);
      if (streamKey.groupIndex == groupIndex && streamKey.trackIndex == i) {
        copiedRenditions.add(rendition);
        break;
      }
    }
  }
  return copiedRenditions;
}
