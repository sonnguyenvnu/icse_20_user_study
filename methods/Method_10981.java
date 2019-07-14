public void updateSelectPosition(final int selectPosition){
  post(new Runnable(){
    @Override public void run(){
      doCardClickAnimation(mViewHolders.get(selectPosition),selectPosition);
    }
  }
);
}
