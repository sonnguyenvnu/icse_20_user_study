private static List<Object> generateData(int number){
  List<Object> dummyData=new ArrayList<>(number);
  for (int i=0; i < number; i++) {
    dummyData.add(new Data(i));
  }
  return dummyData;
}
