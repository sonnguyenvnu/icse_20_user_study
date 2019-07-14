private void showThumbnailWithBackground(ItemViewHolder viewHolder,IconDataParcelable iconData,ImageView view,OnImageProcessed errorListener){
  if (iconData.isImageBroken()) {
    viewHolder.genericIcon.setVisibility(View.VISIBLE);
    GlideApp.with(mainFrag).load(R.drawable.ic_broken_image_white_24dp).into(viewHolder.genericIcon);
    GradientDrawable gradientDrawable=(GradientDrawable)viewHolder.genericIcon.getBackground();
    gradientDrawable.setColor(grey_color);
    errorListener.onImageProcessed(true);
    return;
  }
  viewHolder.genericIcon.setVisibility(View.VISIBLE);
  GlideApp.with(mainFrag).load(iconData.loadingImage).into(viewHolder.genericIcon);
  GradientDrawable gradientDrawable=(GradientDrawable)viewHolder.genericIcon.getBackground();
  RequestListener<Drawable> requestListener=new RequestListener<Drawable>(){
    @Override public boolean onLoadFailed(    @Nullable GlideException e,    Object model,    Target target,    boolean isFirstResource){
      new Handler(msg -> {
        viewHolder.genericIcon.setVisibility(View.VISIBLE);
        GlideApp.with(mainFrag).load(R.drawable.ic_broken_image_white_24dp).into(viewHolder.genericIcon);
        return false;
      }
).obtainMessage().sendToTarget();
      gradientDrawable.setColor(grey_color);
      errorListener.onImageProcessed(true);
      return true;
    }
    @Override public boolean onResourceReady(    Drawable resource,    Object model,    Target<Drawable> target,    DataSource dataSource,    boolean isFirstResource){
      viewHolder.genericIcon.setImageDrawable(null);
      viewHolder.genericIcon.setVisibility(View.GONE);
      gradientDrawable.setColor(mainFrag.getResources().getColor(android.R.color.transparent));
      view.setVisibility(View.VISIBLE);
      errorListener.onImageProcessed(false);
      return false;
    }
  }
;
  modelProvider.getPreloadRequestBuilder(iconData).listener(requestListener).into(view);
}
