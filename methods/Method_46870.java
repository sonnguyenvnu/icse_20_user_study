private void setItems(RecyclerView recyclerView,ArrayList<LayoutElementParcelable> arrayList,boolean invalidate){
  if (preloader != null) {
    recyclerView.removeOnScrollListener(preloader);
    preloader=null;
  }
  itemsDigested.clear();
  offset=0;
  stoppedAnimation=false;
  ArrayList<IconDataParcelable> uris=new ArrayList<>(itemsDigested.size());
  for (  LayoutElementParcelable e : arrayList) {
    itemsDigested.add(new ListItem(e.isBack,e));
    uris.add(e != null ? e.iconData : null);
  }
  if (mainFrag.IS_LIST && itemsDigested.size() > 0) {
    itemsDigested.add(new ListItem(EMPTY_LAST_ITEM));
    uris.add(null);
  }
  for (int i=0; i < itemsDigested.size(); i++) {
    itemsDigested.get(i).setAnimate(false);
  }
  if (getBoolean(PREFERENCE_SHOW_HEADERS)) {
    createHeaders(invalidate,uris);
  }
  sizeProvider=new RecyclerPreloadSizeProvider(this);
  modelProvider=new RecyclerPreloadModelProvider(mainFrag,uris);
  preloader=new RecyclerViewPreloader<>(GlideApp.with(mainFrag),modelProvider,sizeProvider,GlideConstants.MAX_PRELOAD_FILES);
  recyclerView.addOnScrollListener(preloader);
}
