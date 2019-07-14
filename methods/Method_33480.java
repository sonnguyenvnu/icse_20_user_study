/** 
 * ??titlebar??
 */
private void setImgHeaderBg(String imgUrl){
  if (!TextUtils.isEmpty(imgUrl)) {
    Glide.with(this).load(imgUrl).error(R.drawable.stackblur_default).transform(new BlurTransformation(40,8)).listener(new RequestListener<Drawable>(){
      @Override public boolean onLoadFailed(      @Nullable GlideException e,      Object model,      Target<Drawable> target,      boolean isFirstResource){
        return false;
      }
      @Override public boolean onResourceReady(      Drawable resource,      Object model,      Target<Drawable> target,      DataSource dataSource,      boolean isFirstResource){
        bindingTitleView.tbBaseTitle.setBackgroundColor(Color.TRANSPARENT);
        bindingTitleView.ivBaseTitlebarBg.setImageAlpha(0);
        bindingTitleView.ivBaseTitlebarBg.setVisibility(View.VISIBLE);
        return false;
      }
    }
).into(bindingTitleView.ivBaseTitlebarBg);
  }
}
