private static boolean checkPlaylistHeader(BufferedReader reader) throws IOException {
  int last=reader.read();
  if (last == 0xEF) {
    if (reader.read() != 0xBB || reader.read() != 0xBF) {
      return false;
    }
    last=reader.read();
  }
  last=skipIgnorableWhitespace(reader,true,last);
  int playlistHeaderLength=PLAYLIST_HEADER.length();
  for (int i=0; i < playlistHeaderLength; i++) {
    if (last != PLAYLIST_HEADER.charAt(i)) {
      return false;
    }
    last=reader.read();
  }
  last=skipIgnorableWhitespace(reader,false,last);
  return Util.isLinebreak(last);
}
