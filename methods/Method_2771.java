/** 
 * ???????????raw text?
 * @param delimiter ????????
 * @return
 */
public String text(String delimiter){
  if (delimiter == null)   delimiter="";
  StringBuilder sb=new StringBuilder(size() * 3);
  for (  IWord word : this) {
    if (word instanceof CompoundWord) {
      for (      Word child : ((CompoundWord)word).innerList) {
        sb.append(child.getValue()).append(delimiter);
      }
    }
 else {
      sb.append(word.getValue()).append(delimiter);
    }
  }
  sb.setLength(sb.length() - delimiter.length());
  return sb.toString();
}
