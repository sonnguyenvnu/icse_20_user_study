private void bindBadgeListHolder(RecyclerView.ViewHolder holder,Book book,Rating rating){
  BadgeListHolder badgeListHolder=(BadgeListHolder)holder;
  badgeListHolder.badgeListLayout.setTop250(null);
  badgeListHolder.badgeListLayout.setRating(rating,book);
  badgeListHolder.badgeListLayout.setGenre(0,null,CollectableItem.Type.BOOK);
}
