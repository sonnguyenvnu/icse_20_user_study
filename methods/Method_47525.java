public void showSendFileScreen(@NonNull String archiveFilename){
  File file=new File(archiveFilename);
  Uri fileUri=getUriForFile(activity,"org.isoron.uhabits",file);
  Intent intent=new Intent();
  intent.setAction(Intent.ACTION_SEND);
  intent.setType("application/zip");
  intent.putExtra(Intent.EXTRA_STREAM,fileUri);
  intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
  activity.startActivity(intent);
}
