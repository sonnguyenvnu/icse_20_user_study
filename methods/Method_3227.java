protected Result getTargetVector(){
  final int words=vectorsReader.getNumWords();
  final int size=vectorsReader.getSize();
  String[] input=null;
  while ((input=nextWords(1,"Enter a word")) != null) {
    float[] vec=null;
    int bi=-1;
    double len=0;
    for (int i=0; i < words; i++) {
      if (input[0].equals(vectorsReader.getWord(i))) {
        bi=i;
        System.out.printf("\nWord: %s  Position in vocabulary: %d\n",input[0],bi);
        vec=new float[size];
        for (int j=0; j < size; j++) {
          vec[j]=vectorsReader.getMatrixElement(bi,j);
          len+=vec[j] * vec[j];
        }
      }
    }
    if (vec == null) {
      System.out.printf("%s : Out of dictionary word!\n",input[0]);
      continue;
    }
    len=Math.sqrt(len);
    for (int i=0; i < size; i++) {
      vec[i]/=len;
    }
    return new Result(vec,new int[]{bi});
  }
  return null;
}
