/** 
 * To add additional keywords, or to grab them from another mode, override this function. If your mode has no keywords, return a zero length array.
 */
public File[] getKeywordFiles(){
  return new File[]{new File(folder,"keywords.txt")};
}
