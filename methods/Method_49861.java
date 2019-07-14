private static void notifyOfDownload(Context context){
  BroadcastUtils.sendExplicitBroadcast(context,new Intent(),Transaction.NOTIFY_OF_MMS);
}
