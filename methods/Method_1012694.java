@Override public synchronized void resolve(){
  if (getFormat() == null || !getFormat().isVideo()) {
    return;
  }
  boolean found=false;
  if (!found) {
    if (getMedia() == null) {
      setMedia(new DLNAMediaInfo());
    }
    found=!getMedia().isMediaparsed() && !getMedia().isParsing();
    if (getFormat() != null) {
      InputFile input=new InputFile();
      input.setPush(this);
      input.setSize(length());
      getFormat().parse(getMedia(),input,getType(),null);
      if (getMedia() != null && getMedia().isSLS()) {
        setFormat(getMedia().getAudioVariantFormat());
      }
    }
  }
  super.resolve();
}
