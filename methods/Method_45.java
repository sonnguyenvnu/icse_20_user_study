public List<String> readBinaryWatch(int num){
  ArrayList<String> allTimes=new ArrayList<String>();
  for (int i=0; i < 12; i++) {
    for (int j=0; j < 60; j++) {
      if (Integer.bitCount(i * 64 + j) == num) {
        allTimes.add(String.format("%d:%02d",i,j));
      }
    }
  }
  return allTimes;
}
