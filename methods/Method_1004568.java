/** 
 * Search for a tile bitmap into the list of providers and put it in the memory cache
 */
private void search(final long pMapTileIndex){
  for (  final MapTileModuleProviderBase provider : mProviders) {
    try {
      if (provider instanceof MapTileDownloader) {
        final ITileSource tileSource=((MapTileDownloader)provider).getTileSource();
        if (tileSource instanceof OnlineTileSourceBase) {
          if (!((OnlineTileSourceBase)tileSource).getTileSourcePolicy().acceptsPreventive()) {
            continue;
          }
        }
      }
      final Drawable drawable=provider.getTileLoader().loadTile(pMapTileIndex);
      if (drawable == null) {
        continue;
      }
      mCache.putTile(pMapTileIndex,drawable);
      return;
    }
 catch (    CantContinueException exception) {
    }
  }
}
