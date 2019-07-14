@Override public void index(API api,Container.Entry entry,Indexes indexes){
  int depth=15;
  try {
    depth=Integer.valueOf(api.getPreferences().get("DirectoryIndexerPreferences.maximumDepth"));
  }
 catch (  NumberFormatException ignore) {
  }
  index(api,entry,indexes,depth);
}
