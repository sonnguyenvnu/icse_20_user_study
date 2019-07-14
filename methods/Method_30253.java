protected void bindRecommendationListHolder(RecyclerView.ViewHolder holder,T item,List<CollectableItem> recommendationList){
  RecommendationListHolder recommendationListHolder=(RecommendationListHolder)holder;
  recommendationListHolder.titleLayout.setOnClickListener(view -> {
  }
);
  RecommendationListAdapter adapter=(RecommendationListAdapter)recommendationListHolder.recommendationList.getAdapter();
  adapter.replace(recommendationList);
}
