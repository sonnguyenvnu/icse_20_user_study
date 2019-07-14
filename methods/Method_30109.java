private void loadImageFromFile(final File file,final ViewHolder holder){
  GlideApp.with(holder.progress.getContext()).asInfo().load(file).listener(new RequestListener<ImageInfo>(){
    @Override public boolean onResourceReady(    ImageInfo resource,    Object model,    Target<ImageInfo> target,    DataSource dataSource,    boolean isFirstResource){
      return false;
    }
    @Override public boolean onLoadFailed(    @Nullable GlideException e,    Object model,    Target<ImageInfo> target,    boolean isFirstResource){
      showError(e,R.string.gallery_load_error,holder);
      return false;
    }
  }
).into(new SimpleTarget<ImageInfo>(){
    @Override public void onResourceReady(    ImageInfo imageInfo,    Transition<? super ImageInfo> transition){
      loadImageIntoView(file,imageInfo,holder);
    }
  }
);
}
