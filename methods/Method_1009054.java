private static void cache(String formatString,int formatIndex,boolean cached){
  lastFormatIndex.set(formatIndex);
  lastFormatString.set(formatString);
  lastCachedResult.set(Boolean.valueOf(cached));
}
