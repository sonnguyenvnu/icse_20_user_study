private void showRoundedThumbnail(ItemViewHolder viewHolder,IconDataParcelable iconData,ImageView view,OnImageProcessed errorListener){
  if (iconData.isImageBroken()) {
    View iconBackground=getBoolean(PREFERENCE_USE_CIRCULAR_IMAGES) ? viewHolder.genericIcon : viewHolder.iconLayout;
    viewHolder.genericIcon.setVisibility(View.VISIBLE);
    iconBackground.setBackgroundColor(grey_color);
    GlideApp.with(mainFrag).load(R.drawable.ic_broken_image_white_24dp).into(viewHolder.genericIcon);
    view.setVisibility(View.INVISIBLE);
    errorListener.onImageProcessed(true);
    return;
  }
  View iconBackground=getBoolean(PREFERENCE_USE_CIRCULAR_IMAGES) ? viewHolder.genericIcon : viewHolder.iconLayout;
  viewHolder.genericIcon.setVisibility(View.VISIBLE);
  GlideApp.with(mainFrag).load(iconData.loadingImage).into(viewHolder.genericIcon);
  view.setVisibility(View.INVISIBLE);
  RequestListener<Drawable> requestListener=new RequestListener<Drawable>(){
    @Override public boolean onLoadFailed(    @Nullable GlideException e,    Object model,    Target target,    boolean isFirstResource){
      iconBackground.setBackgroundColor(grey_color);
      new Handler(msg -> {
        GlideApp.with(mainFrag).load(R.drawable.ic_broken_image_white_24dp).into(viewHolder.genericIcon);
        return false;
      }
).obtainMessage().sendToTarget();
      errorListener.onImageProcessed(true);
      return true;
    }
    @Override public boolean onResourceReady(    Drawable resource,    Object model,    Target<Drawable> target,    DataSource dataSource,    boolean isFirstResource){
      viewHolder.genericIcon.setImageDrawable(null);
      viewHolder.genericIcon.setVisibility(View.GONE);
      view.setVisibility(View.VISIBLE);
      iconBackground.setBackgroundColor(mainFrag.getResources().getColor(android.R.color.transparent));
      errorListener.onImageProcessed(false);
      return false;
    }
  }
;
  modelProvider.getPreloadRequestBuilder(iconData).listener(requestListener).into(view);
}
