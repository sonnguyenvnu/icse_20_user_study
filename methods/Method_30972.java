public static void loadItemBackdropAndFadeIn(ImageView backdropView,String url,View playView){
  GlideApp.with(backdropView.getContext()).load(url).apply(REQUEST_OPTIONS_LOAD_ITEM_BACKDROP).listener(new RequestListener<Drawable>(){
    @Override public boolean onLoadFailed(    @Nullable GlideException e,    Object model,    Target<Drawable> target,    boolean isFirstResource){
      (e != null ? e : new NullPointerException()).printStackTrace();
      return false;
    }
    @Override public boolean onResourceReady(    Drawable resource,    Object model,    Target<Drawable> target,    DataSource dataSource,    boolean isFirstResource){
      ViewUtils.fadeIn(backdropView);
      if (playView != null) {
        ViewUtils.fadeIn(playView);
      }
      return false;
    }
  }
).into(backdropView);
}
