/** 
 * @since 6.0.2
 */
public void put(final int pZoom,final int pLeft,final int pTop,final int pRight,final int pBottom){
  final int max=1 << pZoom;
  final int spanX=(pRight - pLeft + 1) + (pRight < pLeft ? max : 0);
  final int spanY=(pBottom - pTop + 1) + (pBottom < pTop ? max : 0);
  ensureCapacity(getSize() + spanX * spanY);
  for (int i=0; i < spanX; i++) {
    for (int j=0; j < spanY; j++) {
      final int x=(pLeft + i) % max;
      final int y=(pTop + j) % max;
      put(MapTileIndex.getTileIndex(pZoom,x,y));
    }
  }
}
