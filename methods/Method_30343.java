private void bindBadgeListHolder(RecyclerView.ViewHolder holder,Movie movie,Rating rating){
  BadgeListHolder badgeListHolder=(BadgeListHolder)holder;
  Honor top250Honor=null;
  for (  Honor honor : movie.honors) {
    if (honor.getType() == Honor.Type.TOP_250) {
      top250Honor=honor;
      break;
    }
  }
  badgeListHolder.badgeListLayout.setTop250(top250Honor);
  badgeListHolder.badgeListLayout.setRating(rating,movie);
  badgeListHolder.badgeListLayout.setGenre(R.drawable.movie_badge_white_40dp,CollectionUtils.firstOrNull(movie.genres),CollectableItem.Type.MOVIE);
}
