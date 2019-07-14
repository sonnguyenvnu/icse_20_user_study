@Override public boolean onCreate(){
  final Context context=getContext();
  if (context != null) {
    mRootDir=new File(context.getFilesDir(),"stickers");
    try {
      mRootDir=mRootDir.getCanonicalFile();
    }
 catch (    IOException e) {
      mRootDir=null;
    }
  }
  return mRootDir != null;
}
