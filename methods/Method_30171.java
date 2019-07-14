public void write(CollectableItem item,ItemCollectionState state,int rating,List<String> tags,String comment,List<Long> gamePlatformIds,boolean shareToBroadcast,boolean shareToWeibo,boolean shareToWeChatMoments,Context context){
  add(new CollectItemWriter(item,state,rating,tags,comment,gamePlatformIds,shareToBroadcast,shareToWeibo,shareToWeChatMoments,this),context);
}
