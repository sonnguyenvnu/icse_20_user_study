/** 
 * ?????????????
 * @param sentence ??
 * @param withUndefinedItem ????????????
 * @return
 */
public static List<CommonSynonymDictionary.SynonymItem> convert(List<Term> sentence,boolean withUndefinedItem){
  List<CommonSynonymDictionary.SynonymItem> synonymItemList=new ArrayList<CommonSynonymDictionary.SynonymItem>(sentence.size());
  for (  Term term : sentence) {
    CommonSynonymDictionary.SynonymItem item=get(term.word);
    if (item == null) {
      if (withUndefinedItem) {
        item=CommonSynonymDictionary.SynonymItem.createUndefined(term.word);
        synonymItemList.add(item);
      }
    }
 else {
      synonymItemList.add(item);
    }
  }
  return synonymItemList;
}
