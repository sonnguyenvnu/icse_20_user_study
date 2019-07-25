private void block(CyclicBarrier barrier,final AnActionEvent event){
  try {
    barrier.await();
  }
 catch (  BrokenBarrierException e) {
  }
catch (  InterruptedException e) {
  }
}
