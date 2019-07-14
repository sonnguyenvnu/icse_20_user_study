public static String getRandomTAG(List<String> list){
  Random random=new Random();
  int i=random.nextInt(list.size());
  return list.get(i);
}
