public void bind(User user,List<UserItems> userItemList){
  mUserIdOrUid=user.getIdOrUid();
  UserItems primaryItems=null;
  UserItems secondaryItems=null;
  UserItems tertiaryItems=null;
  CollectableItem.Type type=getItemType();
  for (  UserItems userItems : userItemList) {
    if (userItems.getType() == type) {
switch (userItems.getState()) {
case TODO:
        tertiaryItems=userItems;
      break;
case DOING:
    secondaryItems=userItems;
  break;
case DONE:
primaryItems=userItems;
break;
}
}
}
if (primaryItems == null) {
primaryItems=new UserItems();
primaryItems.type=type.getApiString();
primaryItems.state=ItemCollectionState.DONE.getApiString();
}
bind(primaryItems,secondaryItems,tertiaryItems);
}
