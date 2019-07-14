@Override public void onChanged(int requestCode,Movie newMovie,Rating newRating,List<Photo> newPhotoList,List<SimpleCelebrity> newCelebrityList,List<ItemAwardItem> newAwardList,List<SimpleItemCollection> newItemCollectionList,List<SimpleReview> newReviewList,List<SimpleItemForumTopic> newForumTopicList,List<CollectableItem> newRecommendationList,List<Doulist> newRelatedDoulistList){
  update(newMovie,newRating,newPhotoList,newCelebrityList,newAwardList,newItemCollectionList,newReviewList,newForumTopicList,newRecommendationList,newRelatedDoulistList);
}
