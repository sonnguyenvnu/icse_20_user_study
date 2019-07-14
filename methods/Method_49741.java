static boolean copyBinaryFromAssetsToData(Context context,String fileNameFromAssets,String outputFileName){
  File filesDirectory=getFilesDirectory(context);
  InputStream is;
  try {
    is=context.getAssets().open(fileNameFromAssets);
    final FileOutputStream os=new FileOutputStream(new File(filesDirectory,outputFileName));
    byte[] buffer=new byte[DEFAULT_BUFFER_SIZE];
    int n;
    while (EOF != (n=is.read(buffer))) {
      os.write(buffer,0,n);
    }
    Util.close(os);
    Util.close(is);
    return true;
  }
 catch (  IOException e) {
    Log.e("issue in coping binary from assets to data. ",e);
  }
  return false;
}
