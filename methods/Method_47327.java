private static void applyNewDocFlag(Intent i){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    i.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
  }
 else {
    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME | Intent.FLAG_ACTIVITY_RETAIN_IN_RECENTS);
  }
}
