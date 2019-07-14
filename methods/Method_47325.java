/** 
 * Open a file not supported by Amaze
 * @param f the file
 * @param forcechooser force the chooser to show up even when set default by user
 */
public static void openunknown(File f,Context c,boolean forcechooser,boolean useNewStack){
  Intent chooserIntent=new Intent();
  chooserIntent.setAction(Intent.ACTION_VIEW);
  String type=MimeTypes.getMimeType(f.getPath(),f.isDirectory());
  if (type != null && type.trim().length() != 0 && !type.equals("*/*")) {
    Uri uri=fileToContentUri(c,f);
    if (uri == null)     uri=Uri.fromFile(f);
    chooserIntent.setDataAndType(uri,type);
    Intent activityIntent;
    if (forcechooser) {
      if (useNewStack)       applyNewDocFlag(chooserIntent);
      activityIntent=Intent.createChooser(chooserIntent,c.getString(R.string.openwith));
    }
 else {
      activityIntent=chooserIntent;
      if (useNewStack)       applyNewDocFlag(activityIntent);
    }
    try {
      c.startActivity(activityIntent);
    }
 catch (    ActivityNotFoundException e) {
      e.printStackTrace();
      Toast.makeText(c,R.string.noappfound,Toast.LENGTH_SHORT).show();
      openWith(f,c,useNewStack);
    }
  }
 else {
    openWith(f,c,useNewStack);
  }
}
