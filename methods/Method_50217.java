/** 
 * ?????????????
 */
public RxGalleryFinal cropMaxResultSize(@IntRange(from=100) int width,@IntRange(from=100) int height){
  configuration.setMaxResultSize(width,height);
  return this;
}
