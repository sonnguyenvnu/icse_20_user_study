private static void copy(ArrayList<Integer> list,int[] array,int startIndex){
  for (  int num : list) {
    array[startIndex++]=num;
  }
}
