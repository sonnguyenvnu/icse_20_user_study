@Override public void setList(final List<CommentItem> list){
  runThread(TAG + "setList",new Runnable(){
    @Override public void run(){
      final List<CommentItem> list_=CommentUtil.toDoubleLevelList(list);
      runUiThread(new Runnable(){
        @Override public void run(){
          setList(new AdapterCallBack<CommentAdapter>(){
            @Override public CommentAdapter createAdapter(){
              return new CommentAdapter(context,MomentActivity.this);
            }
            @Override public void refreshAdapter(){
              adapter.refresh(list_);
            }
          }
);
        }
      }
);
    }
  }
);
}
