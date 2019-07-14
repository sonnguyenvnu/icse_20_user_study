/** 
 * ?????????????
 * @param sentence ??
 * @param withUndefinedItem ????????????
 * @return
 */
public static List<Long[]> convert(List<Term> sentence,boolean withUndefinedItem){
  List<Long[]> synonymItemList=new ArrayList<Long[]>(sentence.size());
  for (  Term term : sentence) {
    if (term.nature == null)     continue;
    String nature=term.nature.toString();
    char firstChar=nature.charAt(0);
switch (firstChar) {
case 'm':
{
        if (!TextUtility.isAllChinese(term.word))         continue;
      }
    break;
case 'w':
{
    continue;
  }
}
if (CoreStopWordDictionary.contains(term.word)) continue;
Long[] item=get(term.word);
if (item == null) {
if (withUndefinedItem) {
  item=new Long[]{Long.MAX_VALUE / 3};
  synonymItemList.add(item);
}
}
 else {
synonymItemList.add(item);
}
}
return synonymItemList;
}
