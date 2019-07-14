@Override public void onAction(View actionView,final int position){
  final Song song=mAdapter.getItem(position);
  PopupMenu actionMenu=new PopupMenu(this,actionView,Gravity.END | Gravity.BOTTOM);
  actionMenu.inflate(R.menu.music_action);
  actionMenu.getMenu().findItem(R.id.menu_item_delete).setVisible(!isFolder);
  actionMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
    @Override public boolean onMenuItemClick(    MenuItem item){
switch (item.getItemId()) {
case R.id.menu_item_add_to_play_list:
        new AddToPlayListDialogFragment().setCallback(new AddToPlayListDialogFragment.Callback(){
          @Override public void onPlayListSelected(          PlayList playList){
            if (playList.getId() == mPlayList.getId())             return;
            mPresenter.addSongToPlayList(song,playList);
          }
        }
).show(getSupportFragmentManager().beginTransaction(),"AddToPlayList");
      break;
case R.id.menu_item_delete:
    mDeleteIndex=position;
  mPresenter.delete(song,mPlayList);
break;
}
return true;
}
}
);
actionMenu.show();
}
