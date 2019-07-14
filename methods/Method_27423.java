public boolean isCallingApi(long id,int vId){
  ReactionsModel reactionsModel=getReactionsMap().get(id);
  if (reactionsModel == null || InputHelper.isEmpty(reactionsModel.getContent())) {
    return false;
  }
  ReactionTypes type=ReactionTypes.get(vId);
  return type != null && type.getContent().equals(reactionsModel.getContent()) && reactionsModel.isCallingApi();
}
