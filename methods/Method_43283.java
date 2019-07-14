public BiMap<BitmexPrompt,String> getActivePrompts(List<BitmexTicker> tickers) throws ExchangeException {
  Map<String,BitmexPrompt> bitmexSymbolsToIntervalsMap=new HashMap<String,BitmexPrompt>();
  BiMap<BitmexPrompt,String> bitmexPromptsToSymbolsMap=HashBiMap.create();
  BitmexSymbolsAndPromptsResult promptsAndSymbolsResults=getActiveIntervals();
  for (int i=0; i < promptsAndSymbolsResults.getIntervals().size(); i++) {
    String interval=promptsAndSymbolsResults.getIntervals().get(i);
    BitmexPrompt prompt=BitmexPrompt.valueOf(interval.split("\\:")[1].toUpperCase());
    bitmexSymbolsToIntervalsMap.put(promptsAndSymbolsResults.getSymbols().get(i),prompt);
  }
  for (  BitmexTicker ticker : tickers) {
    String promptSymbol=ticker.getSymbol().replaceFirst(ticker.getRootSymbol(),"");
    BitmexPrompt prompt=bitmexSymbolsToIntervalsMap.get(ticker.getSymbol());
    if (promptSymbol != null && prompt != null && prompt != BitmexPrompt.PERPETUAL && !bitmexPromptsToSymbolsMap.containsKey(prompt)) {
      try {
        bitmexPromptsToSymbolsMap.put(prompt,promptSymbol);
      }
 catch (      Exception e) {
      }
    }
  }
  return bitmexPromptsToSymbolsMap;
}
