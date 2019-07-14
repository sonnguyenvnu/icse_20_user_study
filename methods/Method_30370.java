private void bindBadgeListHolder(RecyclerView.ViewHolder holder,Music music,Rating rating){
  BadgeListHolder badgeListHolder=(BadgeListHolder)holder;
  badgeListHolder.badgeListLayout.setTop250(null);
  badgeListHolder.badgeListLayout.setRating(rating,music);
  badgeListHolder.badgeListLayout.setGenre(R.drawable.music_badge_white_40dp,CollectionUtils.firstOrNull(music.genres),CollectableItem.Type.MUSIC);
}
