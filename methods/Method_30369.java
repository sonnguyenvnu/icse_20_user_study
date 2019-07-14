private void bindHeaderHolder(RecyclerView.ViewHolder holder,Music music){
  HeaderHolder headerHolder=(HeaderHolder)holder;
  boolean coverVisible=ViewUtils.isInLandscape(headerHolder.coverImage.getContext());
  ViewUtils.setVisibleOrGone(headerHolder.coverImage,coverVisible);
  if (coverVisible) {
    headerHolder.coverImage.setRatio(1,1);
    ImageUtils.loadImage(headerHolder.coverImage,music.cover);
    headerHolder.coverImage.setOnClickListener(view -> {
      Context context=view.getContext();
      context.startActivity(GalleryActivity.makeIntent(music.cover,context));
    }
);
  }
  headerHolder.titleText.setText(music.title);
  Context context=RecyclerViewUtils.getContext(holder);
  String slashDelimiter=context.getString(R.string.item_information_delimiter_slash);
  headerHolder.artistsText.setText(StringCompat.join(slashDelimiter,music.getArtistNames()));
  String spaceDelimiter=context.getString(R.string.item_information_delimiter_space);
  String detail=StringUtils.joinNonEmpty(spaceDelimiter,music.getYearMonth(context),StringCompat.join(slashDelimiter,music.publishers));
  headerHolder.detailText.setText(detail);
  headerHolder.genresText.setText(StringCompat.join(slashDelimiter,music.genres));
}
