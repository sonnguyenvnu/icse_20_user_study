public static boolean isShowingImage(TLRPC.BotInlineResult object){
  boolean result=false;
  if (Instance != null) {
    result=Instance.isVisible && !Instance.disableShowCheck && object != null && Instance.currentBotInlineResult != null && object.id == Instance.currentBotInlineResult.id;
  }
  return result;
}
