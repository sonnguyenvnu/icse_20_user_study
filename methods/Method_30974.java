public static void loadImageWithRatio(RatioImageView view,SizedImageItem image){
  view.setRatio(image.getMediumWidth(),image.getMediumHeight());
  GlideApp.with(view.getContext()).load(image.getMediumUrl()).apply(REQUEST_OPTIONS_LOAD_IMAGE_WITH_RATIO).transition(DrawableTransitionOptions.withCrossFade(ViewUtils.getShortAnimTime(view))).into(view);
}
