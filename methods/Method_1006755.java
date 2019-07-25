@Override public void run(){
  for (int i=0; i <= 10; i++) {
    System.out.printf("%s: %d * %d = %d\n",Thread.currentThread().getName(),number,i,i * number);
  }
}
