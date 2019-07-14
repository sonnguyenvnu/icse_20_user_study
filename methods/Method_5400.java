private static Format[] toFormats(List<HlsMasterPlaylist.HlsUrl> hlsUrls){
  Format[] formats=new Format[hlsUrls.size()];
  for (int i=0; i < hlsUrls.size(); i++) {
    formats[i]=hlsUrls.get(i).format;
  }
  return formats;
}
