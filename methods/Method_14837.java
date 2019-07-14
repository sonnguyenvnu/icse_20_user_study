@Override public void setList(final List<MomentItem> list){
  runThread(TAG + "setList",new Runnable(){
    @Override public void run(){
      if (list != null) {
        for (        MomentItem item : list) {
          if (item != null) {
            item.setCommentItemList(CommentUtil.toSingleLevelList(item.getCommentItemList()));
          }
        }
      }
      runUiThread(new Runnable(){
        @Override public void run(){
          setList(new AdapterCallBack<MomentAdapter>(){
            @Override public MomentAdapter createAdapter(){
              return new MomentAdapter(context);
            }
            @Override public void refreshAdapter(){
              adapter.refresh(list);
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
