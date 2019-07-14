public WallProviderStatValue getWallStatValue(boolean reset){
  for (  Filter filter : this.filters) {
    if (filter instanceof WallFilter) {
      WallFilter wallFilter=(WallFilter)filter;
      return wallFilter.getProvider().getStatValue(reset);
    }
  }
  return null;
}
