public void execute() throws Pausable {
  while (true) {
    String m=mb.get();
    if (logging)     System.out.println(" Proc # " + num + ", iters left = " + times);
    if (--times == 0) {
      if (num == 1) {
        long elapsedTime=System.currentTimeMillis() - startTime;
        System.out.println("Elapsed time: " + elapsedTime + " ms");
        System.exit(0);
      }
    }
    prev.put(m);
  }
}
