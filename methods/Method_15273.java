public static void openFile(Activity context,File file){
  if (context == null) {
    Log.e(TAG,"openFile  context == null >> return;");
    return;
  }
  Log.e("OpenFile",file.getName());
  Intent intent=new Intent();
  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
  intent.setAction(android.content.Intent.ACTION_VIEW);
  intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
  context.startActivity(intent);
}
