public BitmexSymbolsAndPromptsResult getActiveIntervals(){
  return updateRateLimit(() -> bitmex.getPromptsAndSymbols());
}
