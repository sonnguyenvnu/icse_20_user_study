public static void shareFiles(ArrayList<File> a,Activity c,AppTheme appTheme,int fab_skin){
  ArrayList<Uri> uris=new ArrayList<>();
  boolean b=true;
  for (  File f : a) {
    uris.add(Uri.fromFile(f));
  }
  String mime=MimeTypes.getMimeType(a.get(0).getPath(),a.get(0).isDirectory());
  if (a.size() > 1)   for (  File f : a) {
    if (!mime.equals(MimeTypes.getMimeType(f.getPath(),f.isDirectory()))) {
      b=false;
    }
  }
  if (!b || mime == (null))   mime="*/*";
  try {
    new ShareTask(c,uris,appTheme,fab_skin).execute(mime);
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
}
