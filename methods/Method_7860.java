private void saveCurrentPagePosition(){
  if (currentPage == null) {
    return;
  }
  int position=layoutManager[0].findFirstVisibleItemPosition();
  if (position != RecyclerView.NO_POSITION) {
    int offset;
    View view=layoutManager[0].findViewByPosition(position);
    if (view != null) {
      offset=view.getTop();
    }
 else {
      offset=0;
    }
    SharedPreferences.Editor editor=ApplicationLoader.applicationContext.getSharedPreferences("articles",Activity.MODE_PRIVATE).edit();
    String key="article" + currentPage.id;
    editor.putInt(key,position).putInt(key + "o",offset).putBoolean(key + "r",AndroidUtilities.displaySize.x > AndroidUtilities.displaySize.y).commit();
  }
}
