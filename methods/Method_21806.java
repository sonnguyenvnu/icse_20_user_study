/** 
 * Use  {@link #getTileWidth()} or {@link #getTileHeight()} instead. This method is deprecatedand will just return the largest of the two sizes.
 * @return tile height or width, whichever is larger
 */
@Deprecated public int getTileSize(){
  return Math.max(tileHeight,tileWidth);
}
