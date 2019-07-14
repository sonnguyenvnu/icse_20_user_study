@Override protected List<Pair<String,String>> makeInformationData(){
  List<Pair<String,String>> data=new ArrayList<>();
  addTextListToData(R.string.item_introduction_book_authors,mItem.authors,data);
  addTextListToData(R.string.item_introduction_book_presses,mItem.presses,data);
  addTextListToData(R.string.item_introduction_book_subtitles,mItem.subtitles,data);
  addTextListToData(R.string.item_introduction_book_translators,mItem.translators,data);
  addTextListToData(R.string.item_introduction_book_release_dates,mItem.releaseDates,data);
  addTextListToData(R.string.item_introduction_book_page_counts,mItem.pageCounts,data);
  addTextListToData(R.string.item_introduction_book_prices,mItem.prices,data);
  return data;
}
