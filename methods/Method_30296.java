private void bindHeaderHolder(RecyclerView.ViewHolder holder,Game game){
  HeaderHolder headerHolder=(HeaderHolder)holder;
  headerHolder.coverImage.setRatio(2,3);
  ImageUtils.loadImage(headerHolder.coverImage,game.cover.getLargeUrl());
  headerHolder.coverImage.setOnClickListener(view -> {
    Context context=view.getContext();
    context.startActivity(GalleryActivity.makeIntent(game.cover,context));
  }
);
  headerHolder.titleText.setText(game.title);
  Context context=RecyclerViewUtils.getContext(holder);
  String slashDelimiter=context.getString(R.string.item_information_delimiter_slash);
  headerHolder.platformsText.setText(StringCompat.join(slashDelimiter,game.getPlatformNames()));
  String spaceDelimiter=context.getString(R.string.item_information_delimiter_space);
  String detail=StringUtils.joinNonEmpty(spaceDelimiter,game.getYearMonth(context),StringCompat.join(slashDelimiter,game.developers));
  headerHolder.detailText.setText(detail);
  headerHolder.genresText.setText(StringCompat.join(slashDelimiter,game.genres));
}
