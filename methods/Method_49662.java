private void openShareMenu(){
  PopupMenu popup=new PopupMenu(mView.getContext(),mView.getShareButton());
  popup.setOnMenuItemClickListener(this);
  MenuInflater inflater=popup.getMenuInflater();
  inflater.inflate(R.menu.share_menu,popup.getMenu());
  popup.show();
}
