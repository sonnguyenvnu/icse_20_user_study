private static List<Integer> asList(int[] array){
  List<Integer> list=null;
  if (array != null) {
    list=new ArrayList<>(array.length);
    for (    int i : array) {
      list.add(i);
    }
  }
  return list;
}
