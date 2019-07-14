protected void onAttachScrollListener(){
  mList.addOnScrollListener(new OnVerticalScrollListener(){
    @Override public void onScrolledToBottom(){
      mResource.load(true);
    }
  }
);
}
