/** 
 * Open file from OTG
 */
public static void openunknown(DocumentFile f,Context c,boolean forcechooser,boolean useNewStack){
  Intent chooserIntent=new Intent();
  chooserIntent.setAction(Intent.ACTION_VIEW);
  chooserIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
  String type=f.getType();
  if (type != null && type.trim().length() != 0 && !type.equals("*/*")) {
    chooserIntent.setDataAndType(f.getUri(),type);
    Intent activityIntent;
    if (forcechooser) {
      if (useNewStack)       applyNewDocFlag(chooserIntent);
      activityIntent=Intent.createChooser(chooserIntent,c.getString(R.string.openwith));
    }
 else {
      activityIntent=chooserIntent;
      if (useNewStack)       applyNewDocFlag(chooserIntent);
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
