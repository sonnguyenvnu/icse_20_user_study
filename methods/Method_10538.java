private void initRoundProgress(){
  progress=0;
  mRxRoundProgress1.setProgress(progress);
  mRxRoundProgress1.setMax(getMax(money));
  downLoadThread2=new Thread(new Runnable(){
    @Override public void run(){
      try {
        while (!downLoadThread2.isInterrupted()) {
          while (progress < mRxRoundProgress1.getMax()) {
            progress+=mRxRoundProgress1.getMax() * 0.01;
            if (progress < mRxRoundProgress1.getMax()) {
              mRxRoundProgress1.setProgress(progress);
            }
            Thread.sleep(8);
          }
          while (progress > 0) {
            progress-=mRxRoundProgress1.getMax() * 0.01;
            if (progress > 0) {
              mRxRoundProgress1.setProgress(progress);
            }
            Thread.sleep(8);
          }
          if (money != 0) {
            while (progress < money) {
              progress+=money * 0.01;
              mRxRoundProgress1.setProgress(progress);
              Thread.sleep(10);
            }
          }
          mRxRoundProgress1.setProgress(money);
        }
      }
 catch (      InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
);
  downLoadThread2.start();
}
