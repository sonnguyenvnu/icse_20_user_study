private void loadBackerAvatar(final @NonNull String url){
  Picasso.with(this).load(url).transform(new CircleTransformation()).into(this.avatarImageView);
}
