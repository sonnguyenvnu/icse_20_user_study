/** 
 * ????????????
 * @param args
 * @return
 */
public static StringDictionary combine(StringDictionary... args){
  StringDictionary[] dictionaries=args.clone();
  StringDictionary mainDictionary=dictionaries[0];
  for (int i=1; i < dictionaries.length; ++i) {
    mainDictionary.combine(dictionaries[i]);
  }
  return mainDictionary;
}
