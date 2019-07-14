@Override public void onFailure(FrescoState frescoState,DataSource<CloseableReference<CloseableImage>> dataSource){
  Drawable errorDrawable=mFrescoContext.getHierarcher().buildErrorDrawable(frescoState.getResources(),frescoState.getImageOptions());
  displayErrorImage(frescoState,errorDrawable);
  frescoState.onFailure(frescoState.getId(),errorDrawable,dataSource.getFailureCause());
}
