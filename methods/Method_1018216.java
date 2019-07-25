/** 
 * word item filter
 * @param word
 * @return  boolean
 */
protected boolean filter(IWord word){
switch (word.getType()) {
case IWord.T_BASIC_LATIN:
case IWord.T_LETTER_NUMBER:
case IWord.T_OTHER_NUMBER:
case IWord.T_CJK_PINYIN:
case IWord.T_PUNCTUATION:
case IWord.T_UNRECOGNIZE_WORD:
{
      return false;
    }
}
String[] poss=word.getPartSpeech();
if (poss == null) return true;
char pos=poss[0].charAt(0);
switch (pos) {
case 'e':
{
    if (poss[0].equals("en"))     return true;
    return false;
  }
case 'q':
case 'b':
case 'r':
case 'z':
case 'p':
case 'c':
case 'u':
case 'y':
case 'd':
case 'o':
case 'g':
case 'x':
case 'w':
{
  return false;
}
}
return true;
}
