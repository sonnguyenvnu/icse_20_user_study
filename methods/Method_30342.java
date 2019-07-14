private void bindHeaderHolder(RecyclerView.ViewHolder holder,Movie movie){
  HeaderHolder headerHolder=(HeaderHolder)holder;
  headerHolder.posterImage.setRatio(27,40);
  ImageUtils.loadImage(headerHolder.posterImage,movie.cover);
  headerHolder.posterImage.setOnClickListener(view -> {
    Context context=view.getContext();
    Intent intent;
    if (movie.poster != null) {
      intent=GalleryActivity.makeIntent(movie.poster.image,context);
    }
 else {
      intent=GalleryActivity.makeIntent(movie.cover,context);
    }
    context.startActivity(intent);
  }
);
  headerHolder.titleText.setText(movie.title);
  headerHolder.originalTitleText.setText(movie.originalTitle);
  Context context=RecyclerViewUtils.getContext(holder);
  String spaceDelimiter=context.getString(R.string.item_information_delimiter_space);
  String detail=StringUtils.joinNonEmpty(spaceDelimiter,movie.getYearMonth(context),movie.getEpisodeCountString(),movie.getDurationString());
  headerHolder.detailText.setText(detail);
  String slashDelimiter=context.getString(R.string.item_information_delimiter_slash);
  headerHolder.genresText.setText(StringCompat.join(slashDelimiter,movie.genres));
}
