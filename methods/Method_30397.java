public static boolean sendBroadcast(String topic,Context context){
  return startActivity(makeSendBroadcastIntent(topic),context);
}
