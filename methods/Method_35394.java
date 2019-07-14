@Override public void onAction(View actionView,final int position){
  final Folder folder=mAdapter.getItem(position);
  PopupMenu actionMenu=new PopupMenu(getActivity(),actionView,Gravity.END | Gravity.BOTTOM);
  actionMenu.inflate(R.menu.folders_action);
  actionMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
    @Override public boolean onMenuItemClick(    MenuItem item){
switch (item.getItemId()) {
case R.id.menu_item_add_to_play_list:
        new AddToPlayListDialogFragment().setCallback(new AddToPlayListDialogFragment.Callback(){
          @Override public void onPlayListSelected(          PlayList playList){
            mPresenter.addFolderToPlayList(folder,playList);
          }
        }
).show(getFragmentManager().beginTransaction(),"AddToPlayList");
      break;
case R.id.menu_item_create_play_list:
    PlayList playList=PlayList.fromFolder(folder);
  EditPlayListDialogFragment.editPlayList(playList).setCallback(new EditPlayListDialogFragment.Callback(){
    @Override public void onCreated(    PlayList playList){
    }
    @Override public void onEdited(    PlayList playList){
      mPresenter.createPlayList(playList);
    }
  }
).show(getFragmentManager().beginTransaction(),"CreatePlayList");
break;
case R.id.menu_item_refresh:
mUpdateIndex=position;
mPresenter.refreshFolder(folder);
break;
case R.id.menu_item_delete:
mDeleteIndex=position;
mPresenter.deleteFolder(folder);
break;
}
return true;
}
}
);
actionMenu.show();
}
