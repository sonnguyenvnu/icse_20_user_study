private void addMediaPlaylistDataSpecs(String baseUri,List<HlsUrl> urls,List<DataSpec> out){
  for (int i=0; i < urls.size(); i++) {
    Uri playlistUri=UriUtil.resolveToUri(baseUri,urls.get(i).url);
    out.add(SegmentDownloader.getCompressibleDataSpec(playlistUri));
  }
}
