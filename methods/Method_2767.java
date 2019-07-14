/** 
 * brat standoff format<br> http://brat.nlplab.org/standoff.html
 * @param withComment
 * @return
 */
public String toStandoff(boolean withComment){
  StringBuilder sb=new StringBuilder(size() * 4);
  String delimiter=" ";
  String text=text(delimiter);
  sb.append(text).append('\n');
  int i=1;
  int offset=0;
  for (  IWord word : wordList) {
    assert text.charAt(offset) == word.getValue().charAt(0);
    printWord(word,sb,i,offset,withComment);
    ++i;
    if (word instanceof CompoundWord) {
      int offsetChild=offset;
      for (      Word child : ((CompoundWord)word).innerList) {
        printWord(child,sb,i,offsetChild,withComment);
        offsetChild+=child.length();
        offsetChild+=delimiter.length();
        ++i;
      }
      offset+=delimiter.length() * ((CompoundWord)word).innerList.size();
    }
 else {
      offset+=delimiter.length();
    }
    offset+=word.length();
  }
  return sb.toString();
}
