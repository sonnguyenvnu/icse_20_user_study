@Override public void onAction(View actionView,final int position){
  final PlayList playList=mAdapter.getItem(position);
  PopupMenu actionMenu=new PopupMenu(getActivity(),actionView,Gravity.END | Gravity.BOTTOM);
  actionMenu.inflate(R.menu.play_list_action);
  if (playList.isFavorite()) {
    actionMenu.getMenu().findItem(R.id.menu_item_rename).setVisible(false);
    actionMenu.getMenu().findItem(R.id.menu_item_delete).setVisible(false);
  }
  actionMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
    @Override public boolean onMenuItemClick(    MenuItem item){
      if (item.getItemId() == R.id.menu_item_play_now) {
        PlayListNowEvent playListNowEvent=new PlayListNowEvent(playList,0);
        RxBus.getInstance().post(playListNowEvent);
      }
 else       if (item.getItemId() == R.id.menu_item_rename) {
        mEditIndex=position;
        EditPlayListDialogFragment.editPlayList(playList).setCallback(PlayListFragment.this).show(getFragmentManager().beginTransaction(),"EditPlayList");
      }
 else       if (item.getItemId() == R.id.menu_item_delete) {
        mDeleteIndex=position;
        mPresenter.deletePlayList(playList);
      }
      return true;
    }
  }
);
  actionMenu.show();
}
