@Override protected List<Segment> getSegments(DataSource dataSource,HlsPlaylist playlist,boolean allowIncompleteList) throws IOException {
  String baseUri=playlist.baseUri;
  ArrayList<DataSpec> mediaPlaylistDataSpecs=new ArrayList<>();
  if (playlist instanceof HlsMasterPlaylist) {
    HlsMasterPlaylist masterPlaylist=(HlsMasterPlaylist)playlist;
    addMediaPlaylistDataSpecs(baseUri,masterPlaylist.variants,mediaPlaylistDataSpecs);
    addMediaPlaylistDataSpecs(baseUri,masterPlaylist.audios,mediaPlaylistDataSpecs);
    addMediaPlaylistDataSpecs(baseUri,masterPlaylist.subtitles,mediaPlaylistDataSpecs);
  }
 else {
    mediaPlaylistDataSpecs.add(SegmentDownloader.getCompressibleDataSpec(Uri.parse(baseUri)));
  }
  ArrayList<Segment> segments=new ArrayList<>();
  HashSet<Uri> seenEncryptionKeyUris=new HashSet<>();
  for (  DataSpec mediaPlaylistDataSpec : mediaPlaylistDataSpecs) {
    segments.add(new Segment(0,mediaPlaylistDataSpec));
    HlsMediaPlaylist mediaPlaylist;
    try {
      mediaPlaylist=(HlsMediaPlaylist)loadManifest(dataSource,mediaPlaylistDataSpec);
    }
 catch (    IOException e) {
      if (!allowIncompleteList) {
        throw e;
      }
      continue;
    }
    HlsMediaPlaylist.Segment lastInitSegment=null;
    List<HlsMediaPlaylist.Segment> hlsSegments=mediaPlaylist.segments;
    for (int i=0; i < hlsSegments.size(); i++) {
      HlsMediaPlaylist.Segment segment=hlsSegments.get(i);
      HlsMediaPlaylist.Segment initSegment=segment.initializationSegment;
      if (initSegment != null && initSegment != lastInitSegment) {
        lastInitSegment=initSegment;
        addSegment(mediaPlaylist,initSegment,seenEncryptionKeyUris,segments);
      }
      addSegment(mediaPlaylist,segment,seenEncryptionKeyUris,segments);
    }
  }
  return segments;
}
