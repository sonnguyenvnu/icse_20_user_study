public static int[] evaluateCWS(String developFile,final PerceptronSegmenter segmenter) throws IOException {
  final int[] stat=new int[3];
  Arrays.fill(stat,0);
  IOUtility.loadInstance(developFile,new InstanceHandler(){
    @Override public boolean process(    Sentence sentence){
      List<Word> wordList=sentence.toSimpleWordList();
      String[] wordArray=toWordArray(wordList);
      stat[0]+=wordArray.length;
      String text=com.hankcs.hanlp.utility.TextUtility.combine(wordArray);
      String[] predArray=segmenter.segment(text).toArray(new String[0]);
      stat[1]+=predArray.length;
      int goldIndex=0, predIndex=0;
      int goldLen=0, predLen=0;
      while (goldIndex < wordArray.length && predIndex < predArray.length) {
        if (goldLen == predLen) {
          if (wordArray[goldIndex].equals(predArray[predIndex])) {
            stat[2]++;
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
 else         if (goldLen < predLen) {
          goldLen+=wordArray[goldIndex].length();
          goldIndex++;
        }
 else {
          predLen+=predArray[predIndex].length();
          predIndex++;
        }
      }
      return false;
    }
  }
);
  return stat;
}
