private void bindAvatarImage(ImageView avatarImage,String avatarUrl){
  if (TextUtils.isEmpty(avatarUrl)) {
    avatarImage.setImageResource(R.drawable.avatar_icon_white_inactive_64dp);
    avatarImage.setTag(null);
    return;
  }
  for (  ImageView anotherAvatarImage : mAvatarImages) {
    String anotherAvatarUrl=(String)anotherAvatarImage.getTag();
    if (TextUtils.equals(anotherAvatarUrl,avatarUrl)) {
      setAvatarImageFrom(avatarImage,anotherAvatarImage);
      return;
    }
  }
  ImageUtils.loadNavigationHeaderAvatar(avatarImage,avatarUrl);
}
