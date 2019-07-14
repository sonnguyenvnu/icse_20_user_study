@Override public View createView(Context context){
  actionBar.setBackButtonImage(R.drawable.ic_ab_back);
  actionBar.setAllowOverlayTitle(true);
  actionBar.setTitle(LocaleController.getString("GroupsInCommonTitle",R.string.GroupsInCommonTitle));
  actionBar.setActionBarMenuOnItemClick(new ActionBar.ActionBarMenuOnItemClick(){
    @Override public void onItemClick(    int id){
      if (id == -1) {
        finishFragment();
      }
    }
  }
);
  fragmentView=new FrameLayout(context);
  fragmentView.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
  FrameLayout frameLayout=(FrameLayout)fragmentView;
  emptyView=new EmptyTextProgressView(context);
  emptyView.setText(LocaleController.getString("NoGroupsInCommon",R.string.NoGroupsInCommon));
  frameLayout.addView(emptyView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
  listView=new RecyclerListView(context);
  listView.setEmptyView(emptyView);
  listView.setLayoutManager(layoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
  listView.setAdapter(listViewAdapter=new ListAdapter(context));
  listView.setVerticalScrollbarPosition(LocaleController.isRTL ? RecyclerListView.SCROLLBAR_POSITION_LEFT : RecyclerListView.SCROLLBAR_POSITION_RIGHT);
  frameLayout.addView(listView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
  listView.setOnItemClickListener((view,position) -> {
    if (position < 0 || position >= chats.size()) {
      return;
    }
    TLRPC.Chat chat=chats.get(position);
    Bundle args=new Bundle();
    args.putInt("chat_id",chat.id);
    if (!MessagesController.getInstance(currentAccount).checkCanOpenChat(args,CommonGroupsActivity.this)) {
      return;
    }
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.closeChats);
    presentFragment(new ChatActivity(args),true);
  }
);
  listView.setOnScrollListener(new RecyclerView.OnScrollListener(){
    @Override public void onScrolled(    RecyclerView recyclerView,    int dx,    int dy){
      int firstVisibleItem=layoutManager.findFirstVisibleItemPosition();
      int visibleItemCount=firstVisibleItem == RecyclerView.NO_POSITION ? 0 : Math.abs(layoutManager.findLastVisibleItemPosition() - firstVisibleItem) + 1;
      if (visibleItemCount > 0) {
        int totalItemCount=listViewAdapter.getItemCount();
        if (!endReached && !loading && !chats.isEmpty() && firstVisibleItem + visibleItemCount >= totalItemCount - 5) {
          getChats(chats.get(chats.size() - 1).id,100);
        }
      }
    }
  }
);
  if (loading) {
    emptyView.showProgress();
  }
 else {
    emptyView.showTextView();
  }
  return fragmentView;
}
