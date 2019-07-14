public static void start(Uri uri,File file,Context context){
  Intent intent=new Intent(context,SaveImageService.class).putExtra(EXTRA_URI,uri).putExtra(EXTRA_FILE,file);
  context.startService(intent);
}
