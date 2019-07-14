public static PlayList generateFavoritePlayList(Context context){
  PlayList favorite=new PlayList();
  favorite.setFavorite(true);
  favorite.setName(context.getString(R.string.mp_play_list_favorite));
  return favorite;
}
