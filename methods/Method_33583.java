private void initScrollView(){
  discoverScrollView.setVisibility(View.VISIBLE);
  discoverScrollView.setCallBack_scrollChanged(new CallBack_ScrollChanged(){
    @Override public void onScrollChanged(    int scrolledY){
      scrollChangeHeader(scrolledY);
    }
  }
);
}
