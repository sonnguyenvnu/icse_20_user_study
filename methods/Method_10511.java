private void AuthCode(final TextView view,final int timeSecond){
  mRunnable=new Runnable(){
    @Override public void run(){
      Handler.postDelayed(mRunnable,1000);
      view.setText(mSumNum + "");
      view.setEnabled(false);
      mSumNum--;
      if (mSumNum < 0) {
        view.setText(0 + "");
        view.setEnabled(true);
        Message message=new Message();
        message.what=60000;
        mHandler.sendMessage(message);
        Handler.removeCallbacks(mRunnable);
        AuthCode(mTvTimeSecond,second);
      }
    }
  }
;
  Handler.postDelayed(mRunnable,1000);
}
