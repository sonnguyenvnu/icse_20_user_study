/** 
 * ????????
 * @return boolean
 */
public boolean isStopWord(char[] charArray,int begin,int length){
  return singleton._StopWords.match(charArray,begin,length).isMatch();
}
