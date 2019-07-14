/** 
 * ??titlebar??
 */
private void setData(){
  if (subjectsBean != null) {
    Glide.with(this).load(subjectsBean.getImages().getLarge()).error(R.drawable.stackblur_default).transform(new BlurTransformation(25,5)).listener(new RequestListener<Drawable>(){
      @Override public boolean onLoadFailed(      @Nullable GlideException e,      Object model,      Target<Drawable> target,      boolean isFirstResource){
        return false;
      }
      @Override public boolean onResourceReady(      Drawable resource,      Object model,      Target<Drawable> target,      DataSource dataSource,      boolean isFirstResource){
        binding.titleToolBar.setBackgroundColor(Color.TRANSPARENT);
        binding.ivTitleHeadBg.setImageAlpha(0);
        binding.ivTitleHeadBg.setVisibility(View.VISIBLE);
        return false;
      }
    }
).into(binding.ivTitleHeadBg);
  }
}
