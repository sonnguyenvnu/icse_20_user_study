/** 
 * ?????????
 * @param word
 * @param natureWithFrequency
 * @return
 */
public static boolean setAttribute(String word,String natureWithFrequency){
  CoreDictionary.Attribute attribute=CoreDictionary.Attribute.create(natureWithFrequency);
  return setAttribute(word,attribute);
}
