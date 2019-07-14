/** 
 * ?????????
 * @param word
 * @param natures
 * @return
 */
public static boolean setAttribute(String word,String... natures){
  if (natures == null)   return false;
  Nature[] natureArray=new Nature[natures.length];
  for (int i=0; i < natureArray.length; i++) {
    natureArray[i]=Nature.create(natures[i]);
  }
  return setAttribute(word,natureArray);
}
