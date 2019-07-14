/** 
 * ?????????,??????????
 * @param key ??
 * @param attribute ?????
 * @param map ?????map
 * @param rewriteTable
 * @return ?????
 */
private static boolean updateAttributeIfExist(String key,CoreDictionary.Attribute attribute,TreeMap<String,CoreDictionary.Attribute> map,TreeMap<Integer,CoreDictionary.Attribute> rewriteTable){
  int wordID=CoreDictionary.getWordID(key);
  CoreDictionary.Attribute attributeExisted;
  if (wordID != -1) {
    attributeExisted=CoreDictionary.get(wordID);
    attributeExisted.nature=attribute.nature;
    attributeExisted.frequency=attribute.frequency;
    attributeExisted.totalFrequency=attribute.totalFrequency;
    rewriteTable.put(wordID,attribute);
    return true;
  }
  attributeExisted=map.get(key);
  if (attributeExisted != null) {
    attributeExisted.nature=attribute.nature;
    attributeExisted.frequency=attribute.frequency;
    attributeExisted.totalFrequency=attribute.totalFrequency;
    return true;
  }
  return false;
}
