/** 
 * Locates first dot after the last slash.
 */
public static int indexOfDotAfterSlash(final String str){
  int slashNdx=str.lastIndexOf('/');
  if (slashNdx == -1) {
    slashNdx=0;
  }
  return str.indexOf('.',slashNdx);
}
