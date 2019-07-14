public static boolean isFeedsHintShowed(){
  boolean isFeedsHitShowed=PrefHelper.getBoolean("feeds_hint");
  if (!isFeedsHitShowed) {
    PrefHelper.set("feeds_hint",true);
  }
  return isFeedsHitShowed;
}
