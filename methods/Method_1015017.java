public void bind(String targetName,String targetPortrait,Message message){
  nameTextView.setText(targetName);
  Glide.with(getContext()).load(targetPortrait).apply(new RequestOptions().placeholder(R.mipmap.ic_group_cheat).centerCrop()).into(portraitImageView);
  if (message.content instanceof ImageMessageContent) {
    contentTextView.setVisibility(GONE);
    contentImageView.setVisibility(VISIBLE);
    Bitmap bitmap=((ImageMessageContent)message.content).getThumbnail();
    contentImageView.getLayoutParams().width=UIUtils.dip2Px(bitmap.getWidth());
    contentImageView.getLayoutParams().height=UIUtils.dip2Px(bitmap.getHeight());
    contentImageView.setImageBitmap(bitmap);
  }
 else   if (message.content instanceof VideoMessageContent) {
    contentTextView.setVisibility(GONE);
    contentImageView.setVisibility(VISIBLE);
    Bitmap bitmap=((ImageMessageContent)message.content).getThumbnail();
    contentImageView.getLayoutParams().width=UIUtils.dip2Px(bitmap.getWidth());
    contentImageView.getLayoutParams().height=UIUtils.dip2Px(bitmap.getHeight());
    contentImageView.setImageBitmap(bitmap);
  }
 else {
    contentImageView.setVisibility(GONE);
    contentTextView.setVisibility(VISIBLE);
    contentTextView.setText(message.digest());
  }
  invalidate();
}
