@Override public void bind(@NotNull Holder holder){
  holder.image.setImageResource(icons[mType]);
  holder.title.setText(mTitle);
  holder.subtitle.setText(mSubtitle);
}
