public void setWallet(Privacy privacy_){
  this.privacy=privacy_;
  runUiThread(new Runnable(){
    @Override public void run(){
      dismissProgressDialog();
      tvBaseTitle.setText(getTitleName());
      if (privacy == null) {
        privacy=new Privacy();
      }
      tvWalletCount.setText(StringUtil.getPrice(privacy.getBalance(),StringUtil.PRICE_FORMAT_PREFIX));
    }
  }
);
}
