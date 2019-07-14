/** 
 * ?????????
 * @param word
 * @param natures
 * @return
 */
public static boolean setAttribute(String word,Nature... natures){
  if (natures == null)   return false;
  CoreDictionary.Attribute attribute=new CoreDictionary.Attribute(natures,new int[natures.length]);
  Arrays.fill(attribute.frequency,1);
  return setAttribute(word,attribute);
}
