public static InputStream fallback(String path) throws FileNotFoundException {
  try {
    MP3File mp3File=new MP3File(path);
    if (mp3File.hasID3v2Tag()) {
      Artwork art=mp3File.getTag().getFirstArtwork();
      if (art != null) {
        byte[] imageData=art.getBinaryData();
        return new ByteArrayInputStream(imageData);
      }
    }
  }
 catch (  ReadOnlyFileException ignored) {
  }
catch (  InvalidAudioFrameException ignored) {
  }
catch (  TagException ignored) {
  }
catch (  IOException ignored) {
  }
  final File parent=new File(path).getParentFile();
  for (  String fallback : FALLBACKS) {
    File cover=new File(parent,fallback);
    if (cover.exists()) {
      return new FileInputStream(cover);
    }
  }
  return null;
}
