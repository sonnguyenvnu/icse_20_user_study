private void bindGameGuideListHolder(RecyclerView.ViewHolder holder,Game game,List<SimpleReview> gameGuideList){
  bindReviewListHolder(holder,game,gameGuideList,R.string.item_game_guide_list_title,R.string.item_game_guide_list_create,R.string.item_game_guide_list_view_more,view -> {
    UriHandler.open(game.url + "new_review?rtype=G",view.getContext());
  }
,view -> {
    UriHandler.open(game.url + "reviews?rtype=G",view.getContext());
  }
);
}
