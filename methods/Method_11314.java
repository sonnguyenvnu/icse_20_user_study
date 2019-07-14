/** 
 * Enables or disables size caching, enabling it will improve performance where you are animating a value inside TextView. This stores the font size against getText().length() Be careful though while enabling it as 0 takes more space than 1 on some fonts and so on.
 * @param enable enable font size caching
 */
public void setEnableSizeCache(final boolean enable){
  enableSizeCache=enable;
  textCachedSizes.clear();
  adjustTextSize();
}
