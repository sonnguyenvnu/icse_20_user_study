private static void shuffle(int[] a){
  Random rnd=ThreadLocalRandom.current();
  for (int i=a.length - 1; i > 0; i--) {
    int index=rnd.nextInt(i + 1);
    int e=a[index];
    a[index]=a[i];
    a[i]=e;
  }
}
