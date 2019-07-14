/** 
 * Determine whether tile is visible.
 */
private boolean tileVisible(Tile tile){
  float sVisLeft=viewToSourceX(0), sVisRight=viewToSourceX(getWidth()), sVisTop=viewToSourceY(0), sVisBottom=viewToSourceY(getHeight());
  return !(sVisLeft > tile.sRect.right || tile.sRect.left > sVisRight || sVisTop > tile.sRect.bottom || tile.sRect.top > sVisBottom);
}
