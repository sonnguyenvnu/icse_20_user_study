public void enter(){
  ViewUtils.postOnPreDraw(this,new Runnable(){
    @Override public void run(){
      animateEnter();
    }
  }
);
}
