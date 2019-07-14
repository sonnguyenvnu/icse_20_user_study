@Override public void bind(PlayList item,int position){
  if (item.isFavorite()) {
    imageViewAlbum.setImageResource(R.drawable.ic_favorite_yes);
  }
 else {
    imageViewAlbum.setImageDrawable(ViewUtils.generateAlbumDrawable(getContext(),item.getName()));
  }
  textViewName.setText(item.getName());
  textViewInfo.setText(getResources().getQuantityString(R.plurals.mp_play_list_items_formatter,item.getItemCount(),item.getItemCount()));
}
