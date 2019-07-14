/** 
 * ????
 * @param path
 * @param separator
 * @return
 */
public static StringDictionary load(String path,String separator){
  StringDictionary dictionary=new StringDictionary(separator);
  if (dictionary.load(path))   return dictionary;
  return null;
}
