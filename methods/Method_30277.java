@Override public void onChanged(int requestCode,Book newBook,Rating newRating,List<SimpleItemCollection> newItemCollectionList,List<SimpleReview> newReviewList,List<SimpleItemForumTopic> newForumTopicList,List<CollectableItem> newRecommendationList,List<Doulist> newRelatedDoulistList){
  update(newBook,newRating,newItemCollectionList,newReviewList,newForumTopicList,newRecommendationList,newRelatedDoulistList);
}
