public synchronized void hideProgress(){
  mProgressBarEnabled=false;
  mShouldUpdateButtonPosition=true;
  updateBackground();
}
