public static void shareText(String text,Context context){
  AppUtils.startActivityWithChooser(IntentUtils.makeSendText(text),context);
}
