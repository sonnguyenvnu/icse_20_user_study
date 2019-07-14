private void collect(ItemCollectionState state,int rating,List<String> tags,String comment,boolean shareToBroadcast,boolean shareToWeibo){
  CollectItemManager.getInstance().write(mItem.getType(),mItem.id,state,rating,tags,comment,null,shareToBroadcast,shareToWeibo,false,getActivity());
  updateCollectStatus();
}
