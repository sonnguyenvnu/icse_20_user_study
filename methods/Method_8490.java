public void checkColors(){
  for (int a=0, count=attachButtons.size(); a < count; a++) {
    attachButtons.get(a).textView.setTextColor(Theme.getColor(Theme.key_dialogTextGray2));
  }
  lineView.setBackgroundColor(Theme.getColor(Theme.key_dialogGrayLine));
  if (hintTextView != null) {
    Theme.setDrawableColor(hintTextView.getBackground(),Theme.getColor(Theme.key_chat_gifSaveHintBackground));
    hintTextView.setTextColor(Theme.getColor(Theme.key_chat_gifSaveHintText));
  }
  if (mediaBanTooltip != null) {
    Theme.setDrawableColor(mediaBanTooltip.getBackground(),Theme.getColor(Theme.key_chat_attachMediaBanBackground));
    mediaBanTooltip.setTextColor(Theme.getColor(Theme.key_chat_attachMediaBanText));
  }
  if (listView != null) {
    listView.setGlowColor(Theme.getColor(Theme.key_dialogScrollGlow));
    RecyclerView.ViewHolder holder=listView.findViewHolderForAdapterPosition(1);
    if (holder != null) {
      holder.itemView.setBackgroundColor(Theme.getColor(Theme.key_dialogBackgroundGray));
    }
 else {
      adapter.notifyItemChanged(1);
    }
  }
  if (ciclePaint != null) {
    ciclePaint.setColor(Theme.getColor(Theme.key_dialogBackground));
  }
  Theme.setDrawableColor(shadowDrawable,Theme.getColor(Theme.key_dialogBackground));
  if (cameraImageView != null) {
    cameraImageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogCameraIcon),PorterDuff.Mode.MULTIPLY));
  }
  if (attachPhotoRecyclerView != null) {
    RecyclerView.ViewHolder holder=attachPhotoRecyclerView.findViewHolderForAdapterPosition(0);
    if (holder != null && holder.itemView instanceof PhotoAttachCameraCell) {
      ((PhotoAttachCameraCell)holder.itemView).getImageView().setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogCameraIcon),PorterDuff.Mode.MULTIPLY));
    }
  }
  containerView.invalidate();
}
