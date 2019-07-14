private void initScrollViewListener(){
  binding.nsvScrollview.setOnScrollChangeListener(new MyNestedScrollView.ScrollInterface(){
    @Override public void onScrollChange(    int scrollX,    int scrollY,    int oldScrollX,    int oldScrollY){
      scrollChangeHeader(scrollY);
    }
  }
);
}
