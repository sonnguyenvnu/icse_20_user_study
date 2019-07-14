@Override protected List<Pair<String,String>> makeInformationData(){
  List<Pair<String,String>> data=new ArrayList<>();
  addTextListToData(R.string.item_introduction_game_genres,mItem.genres,data);
  addTextListToData(R.string.item_introduction_game_platforms,mItem.getPlatformNames(),data);
  addTextListToData(R.string.item_introduction_game_alternative_titles,mItem.alternativeTitles,data);
  addTextListToData(R.string.item_introduction_game_developers,mItem.developers,data);
  addTextListToData(R.string.item_introduction_game_publishers,mItem.publishers,data);
  addTextToData(R.string.item_introduction_game_release_date,mItem.releaseDate,data);
  return data;
}
