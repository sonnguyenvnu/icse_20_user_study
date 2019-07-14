@Override protected void notifyChanged(int requestCode,Music newItem,Rating newRating,List<Photo> newPhotoList,List<SimpleCelebrity> newCelebrityList,List<ItemAwardItem> newAwardList,List<SimpleItemCollection> newItemCollectionList,List<SimpleReview> newGameGuideList,List<SimpleReview> newReviewList,List<SimpleItemForumTopic> newForumTopicList,List<CollectableItem> newRecommendationList,List<Doulist> newRelatedDoulistList){
  getListener().onChanged(requestCode,newItem,newRating,newItemCollectionList,newReviewList,newForumTopicList,newRecommendationList,newRelatedDoulistList);
}
