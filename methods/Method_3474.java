/** 
 * ???????????
 * @param gold
 * @param pred
 */
public void compare(String gold,String pred){
  String[] wordArray=gold.split("\\s+");
  A_size+=wordArray.length;
  String[] predArray=pred.split("\\s+");
  B_size+=predArray.length;
  int goldIndex=0, predIndex=0;
  int goldLen=0, predLen=0;
  while (goldIndex < wordArray.length && predIndex < predArray.length) {
    if (goldLen == predLen) {
      if (wordArray[goldIndex].equals(predArray[predIndex])) {
        if (dic != null) {
          if (dic.contains(wordArray[goldIndex]))           IV_R+=1;
 else           OOV_R+=1;
        }
        A_cap_B_size++;
        goldLen+=wordArray[goldIndex].length();
        predLen+=wordArray[goldIndex].length();
        goldIndex++;
        predIndex++;
      }
 else {
        goldLen+=wordArray[goldIndex].length();
        predLen+=predArray[predIndex].length();
        goldIndex++;
        predIndex++;
      }
    }
 else     if (goldLen < predLen) {
      goldLen+=wordArray[goldIndex].length();
      goldIndex++;
    }
 else {
      predLen+=predArray[predIndex].length();
      predIndex++;
    }
  }
  if (dic != null) {
    for (    String word : wordArray) {
      if (dic.contains(word))       IV+=1;
 else       OOV+=1;
    }
  }
}
