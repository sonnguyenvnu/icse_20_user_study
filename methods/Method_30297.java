private void bindBadgeListHolder(RecyclerView.ViewHolder holder,Game game,Rating rating){
  BadgeListHolder badgeListHolder=(BadgeListHolder)holder;
  badgeListHolder.badgeListLayout.setTop250(null);
  badgeListHolder.badgeListLayout.setRating(rating,game);
  badgeListHolder.badgeListLayout.setGenre(R.drawable.game_badge_white_40dp,CollectionUtils.firstOrNull(game.genres),CollectableItem.Type.GAME);
}
