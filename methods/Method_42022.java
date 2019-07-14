private void loadAlbumsLazy(Intent intent){
  album=intent.getParcelableExtra(EXTRA_ARGS_ALBUM);
  Media m=intent.getParcelableExtra(EXTRA_ARGS_MEDIA);
  media=new ArrayList<>();
  media.add(m);
  position=0;
  ArrayList<Media> list=new ArrayList<>();
  Disposable disposable=CPHelper.getMedia(getApplicationContext(),album).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).filter(media -> MediaFilter.getFilter(album.filterMode()).accept(media) && !media.equals(m)).subscribe(ma -> {
    int i=Collections.binarySearch(list,ma,MediaComparators.getComparator(album.settings));
    if (i < 0)     i=~i;
    list.add(i,ma);
  }
,throwable -> {
    Log.wtf("asd",throwable);
  }
,() -> {
    int i=Collections.binarySearch(list,m,MediaComparators.getComparator(album.settings));
    if (i < 0)     i=~i;
    list.add(i,m);
    media.clear();
    media.addAll(list);
    adapter.notifyDataSetChanged();
    position=i;
    mViewPager.setCurrentItem(position);
    updatePageTitle(position);
  }
);
  disposeLater(disposable);
}
