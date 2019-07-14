public static void loadAvatar(ImageView view,String url){
  GlideApp.with(view.getContext()).load(url).apply(REQUEST_OPTIONS_LOAD_AVATAR).into(view);
}
