@Override protected List<Pair<String,String>> makeInformationData(){
  List<Pair<String,String>> data=new ArrayList<>();
  addTextListToData(R.string.item_introduction_music_artists,mItem.getArtistNames(),data);
  addTextListToData(R.string.item_introduction_music_genres,mItem.genres,data);
  addTextListToData(R.string.item_introduction_music_types,mItem.types,data);
  addTextListToData(R.string.item_introduction_music_media,mItem.media,data);
  addTextListToData(R.string.item_introduction_music_release_dates,mItem.releaseDates,data);
  addTextListToData(R.string.item_introduction_music_publishers,mItem.publishers,data);
  addTextListToData(R.string.item_introduction_music_disc_counts,mItem.discCounts,data);
  return data;
}
