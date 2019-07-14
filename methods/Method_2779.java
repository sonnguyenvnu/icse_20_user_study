/** 
 * word pos ner
 * @param tagSet
 * @return
 */
public String[][] toWordTagNerArray(NERTagSet tagSet){
  List<String[]> tupleList=Utility.convertSentenceToNER(this,tagSet);
  String[][] result=new String[3][tupleList.size()];
  Iterator<String[]> iterator=tupleList.iterator();
  for (int i=0; i < result[0].length; i++) {
    String[] tuple=iterator.next();
    for (int j=0; j < 3; ++j) {
      result[j][i]=tuple[j];
    }
  }
  return result;
}
