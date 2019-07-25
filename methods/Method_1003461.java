public void save(Bitmap bitmap){
  if (bitmap == null) {
    setResult(RESULT_CANCELED);
    super.finish();
  }
  try {
    Uri uri=getIntent().getParcelableExtra(MediaStore.EXTRA_OUTPUT);
    File bitmapFile=new File(uri.getPath());
    FileOutputStream out=new FileOutputStream(bitmapFile);
    assert bitmap != null;
    bitmap.compress(Bitmap.CompressFormat.PNG,90,out);
    if (bitmapFile.exists()) {
      Intent localIntent=new Intent().setData(Uri.fromFile(bitmapFile));
      setResult(RESULT_OK,localIntent);
    }
 else {
      setResult(RESULT_CANCELED);
    }
    super.finish();
  }
 catch (  Exception e) {
    LogDelegate.w("Bitmap not found",e);
  }
}
