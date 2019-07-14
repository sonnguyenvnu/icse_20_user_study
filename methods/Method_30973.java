public static void loadProfileAvatarAndFadeIn(final ImageView view,String url){
  GlideApp.with(view.getContext()).load(url).apply(REQUEST_OPTIONS_LOAD_PROFILE_AVATAR).listener(new RequestListener<Drawable>(){
    @Override public boolean onLoadFailed(    @Nullable GlideException e,    Object model,    Target<Drawable> target,    boolean isFirstResource){
      (e != null ? e : new NullPointerException()).printStackTrace();
      return false;
    }
    @Override public boolean onResourceReady(    Drawable resource,    Object model,    Target<Drawable> target,    DataSource dataSource,    boolean isFirstResource){
      ViewUtils.fadeIn(view);
      return false;
    }
  }
).into(view);
}
