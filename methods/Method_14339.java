static public InputStream openAndTrackFile(final String fileSource,final File file,final MultiFileReadingProgress progress) throws FileNotFoundException {
  InputStream inputStream=new FileInputStream(file);
  return progress == null ? inputStream : new TrackingInputStream(inputStream){
    @Override protected long track(    long bytesRead){
      long l=super.track(bytesRead);
      progress.readingFile(fileSource,this.bytesRead);
      return l;
    }
  }
;
}
