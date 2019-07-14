private void initRxRoundPd(){
  mRxRoundProgress=0;
  mRxRoundPd1.setMax(mRxRoundPdMax);
  mRxRoundPd2.setMax(mRxRoundPdMax);
  mRxRoundPd3.setMax(mRxRoundPdMax);
  mRxRoundPd4.setMax(mRxRoundPdMax);
  mRxRoundPd5.setMax(mRxRoundPdMax);
  mRxRoundPd6.setMax(mRxRoundPdMax);
  mRxRoundPd7.setMax(mRxRoundPdMax);
  mRxRoundPd8.setMax(mRxRoundPdMax);
  mRxRoundPd9.setMax(mRxRoundPdMax);
  mRxRoundPd10.setMax(mRxRoundPdMax);
  mRxRoundPd11.setMax(mRxRoundPdMax);
  mRxRoundPd12.setMax(mRxRoundPdMax);
  mRxRoundPd13.setMax(mRxRoundPdMax);
  mRxRoundPd14.setMax(mRxRoundPdMax);
  mRxRoundPd15.setMax(mRxRoundPdMax);
  mRxRoundPd16.setMax(mRxRoundPdMax);
  mRxRoundPd17.setMax(mRxRoundPdMax);
  mProgressOne.setMax(mRxRoundPdMax);
  mProgressTwo.setMax(mRxRoundPdMax);
  mProgressThree.setMax(mRxRoundPdMax);
  downLoadRxRoundPdThread=new Thread(new Runnable(){
    @Override public void run(){
      try {
        while (!downLoadRxRoundPdThread.isInterrupted()) {
          while (mRxRoundProgress < mRxRoundPd1.getMax()) {
            mRxRoundProgress+=mRxRoundPd1.getMax() * 0.01;
            if (mRxRoundProgress < mRxRoundPd1.getMax()) {
              Message message=new Message();
              message.what=101;
              mRxRoundPdHandler.sendMessage(message);
            }
            Thread.sleep(8);
          }
          while (mRxRoundProgress > 0) {
            mRxRoundProgress-=mRxRoundPd1.getMax() * 0.01;
            if (mRxRoundProgress > 0) {
              Message message=new Message();
              message.what=101;
              mRxRoundPdHandler.sendMessage(message);
            }
            Thread.sleep(8);
          }
          while (mRxRoundProgress < mRxRoundPdMax) {
            mRxRoundProgress+=mRxRoundPdMax * 0.01;
            Message message=new Message();
            message.what=101;
            mRxRoundPdHandler.sendMessage(message);
            Thread.sleep(10);
          }
        }
      }
 catch (      InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
);
  downLoadRxRoundPdThread.start();
}
