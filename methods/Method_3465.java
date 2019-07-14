/** 
 * ???bad case
 * @param name
 * @return
 */
public static boolean isBadCase(String name){
  Character label=JapanesePersonDictionary.get(name);
  if (label == null)   return false;
  return label.equals(JapanesePersonDictionary.A);
}
