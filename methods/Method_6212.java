CommentOrUnsynchronizedLyrics parseCommentOrUnsynchronizedLyricsFrame(ID3v2FrameBody data) throws IOException, ID3v2Exception {
  ID3v2Encoding encoding=data.readEncoding();
  String language=data.readFixedLengthString(3,ID3v2Encoding.ISO_8859_1);
  String description=data.readZeroTerminatedString(200,encoding);
  String text=data.readFixedLengthString((int)data.getRemainingLength(),encoding);
  return new CommentOrUnsynchronizedLyrics(language,description,text);
}
