public void postSetSelectionFromTop(final int position,final int offset){
  post(new Runnable(){
    @Override public void run(){
      setSelectionFromTop(position,offset);
      requestLayout();
    }
  }
);
}
