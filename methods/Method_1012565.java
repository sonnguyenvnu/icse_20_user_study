private static void shuffle(Object[] arr){
  for (int i=arr.length - 1; i >= 0; i--) {
    int pos=(int)(Math.random() * (i + 1));
    Object t=arr[pos];
    arr[pos]=arr[i];
    arr[i]=t;
  }
}
