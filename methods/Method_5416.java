/** 
 * Creates a factory which always returns the given playlist parser.
 * @param playlistParser The parser to return.
 * @return A factory which always returns the given playlist parser.
 */
private static HlsPlaylistParserFactory createFixedFactory(ParsingLoadable.Parser<HlsPlaylist> playlistParser){
  return new HlsPlaylistParserFactory(){
    @Override public ParsingLoadable.Parser<HlsPlaylist> createPlaylistParser(){
      return playlistParser;
    }
    @Override public ParsingLoadable.Parser<HlsPlaylist> createPlaylistParser(    HlsMasterPlaylist masterPlaylist){
      return playlistParser;
    }
  }
;
}
