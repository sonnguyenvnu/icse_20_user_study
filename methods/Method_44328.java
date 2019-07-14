public Map<String,HitbtcTicker> getHitbtcTickers() throws IOException {
  return hitbtc.getHitbtcTickers().stream().collect(Collectors.toMap(hitbtcTicker -> hitbtcTicker.getSymbol(),hitbtcTicker -> hitbtcTicker));
}
