/** 
 * Initializes this instance. During renderering the current transformation will be leaved unchanged. But the current Clip may be cleared.
 * @param graphics Graphics2D context into which the {@link BasicGraphicalElement BaiscGraphicalElements} arepainted.
 * @return this instance.
 */
public Graphics2DRenderer init(Graphics2D graphics){
  _graphics=graphics;
  _defaultColor=graphics.getColor();
  return this;
}
