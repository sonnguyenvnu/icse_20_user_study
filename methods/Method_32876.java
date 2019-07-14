/** 
 * Reset all indexed colors with the default color from the color theme. 
 */
public void reset(){
  System.arraycopy(COLOR_SCHEME.mDefaultColors,0,mCurrentColors,0,TextStyle.NUM_INDEXED_COLORS);
}
