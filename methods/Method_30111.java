private void showError(@Nullable Exception e,int resId,ViewHolder holder){
  (e != null ? e : new NullPointerException()).printStackTrace();
  holder.errorText.setText(resId);
  ViewUtils.crossfade(holder.progress,holder.errorText);
}
