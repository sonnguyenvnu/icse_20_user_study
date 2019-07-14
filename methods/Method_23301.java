/** 
 * Return the native java.awt.Font associated with this PFont (if any).
 */
public Object getNative(){
  if (subsetting) {
    return null;
  }
  return font;
}
