private void loadAlbum(Album album){
  this.album=album;
  adapter.setupFor(album);
  CPHelper.getMedia(getContext(),album).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).filter(media -> MediaFilter.getFilter(album.filterMode()).accept(media)).subscribe(media -> adapter.add(media),throwable -> {
    refresh.setRefreshing(false);
    Log.wtf("asd",throwable);
  }
,() -> {
    album.setCount(getCount());
    if (getNothingToShowListener() != null)     getNothingToShowListener().changedNothingToShow(getCount() == 0);
    refresh.setRefreshing(false);
  }
);
}
