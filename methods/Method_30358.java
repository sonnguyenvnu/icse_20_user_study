@Override protected List<Pair<String,String>> makeInformationData(){
  List<Pair<String,String>> data=new ArrayList<>();
  addTextToData(R.string.item_introduction_movie_original_title,mItem.originalTitle,data);
  addTextListToData(R.string.item_introduction_movie_genres,mItem.genres,data);
  addTextListToData(R.string.item_introduction_movie_countries,mItem.countries,data);
  addTextListToData(R.string.item_introduction_movie_languages,mItem.languages,data);
  addTextListToData(R.string.item_introduction_movie_release_dates,mItem.releaseDates,data);
  addTextToData(R.string.item_introduction_movie_episode_count,mItem.getEpisodeCountString(),data);
  addTextListToData(R.string.item_introduction_movie_durations,mItem.durations,data);
  addTextListToData(R.string.item_introduction_movie_alternative_titles,mItem.alternativeTitles,data);
  return data;
}
