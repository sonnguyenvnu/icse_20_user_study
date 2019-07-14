private static String sanitizeInputOnce(String input){
  return input.replaceAll(INPUT_INTENT_BLACKLIST_PIPE,"").replaceAll(INPUT_INTENT_BLACKLIST_AMP,"").replaceAll(INPUT_INTENT_BLACKLIST_DOTS,"").replaceAll(INPUT_INTENT_BLACKLIST_COLON,"");
}
