public Settings init(String tag){
  if (tag == null) {
    throw new NullPointerException("tag may not be null");
  }
 else   if (tag.trim().length() == 0) {
    throw new IllegalStateException("tag may not be empty");
  }
 else {
    this.tag=tag;
    this.settings=new Settings();
    return this.settings;
  }
}
