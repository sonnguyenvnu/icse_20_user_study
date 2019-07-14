private void deleteCurrentMedia(){
  Media currentMedia=getCurrentMedia();
  Disposable disposable=MediaHelper.deleteMedia(getApplicationContext(),currentMedia).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(deleted -> {
    media.remove(deleted);
    if (media.size() == 0) {
      displayAlbums();
    }
  }
,err -> {
    Toast.makeText(getApplicationContext(),err.getMessage(),Toast.LENGTH_SHORT).show();
  }
,() -> {
    adapter.notifyDataSetChanged();
    updatePageTitle(mViewPager.getCurrentItem());
  }
);
  disposeLater(disposable);
}
