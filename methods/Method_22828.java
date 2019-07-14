/** 
 * Paint a line. Paints the gutter (with background color and text) then the line (background color and text).
 * @param gfx the graphics context
 * @param tokenMarker
 * @param line 0-based line number
 * @param x horizontal position
 */
@Override protected void paintLine(Graphics gfx,int line,int x,TokenMarkerState marker){
  try {
    super.paintLine(gfx,line,x + Editor.LEFT_GUTTER,marker);
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  paintLeftGutter(gfx,line,x);
  paintErrorLine(gfx,line,x);
}
