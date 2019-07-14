public boolean isPreviouslyReacted(long idOrNumber,@IdRes int vId){
  ReactionsModel reactionsModel=getReactionsMap().get(idOrNumber);
  if (reactionsModel == null || InputHelper.isEmpty(reactionsModel.getContent())) {
    return false;
  }
  ReactionTypes type=ReactionTypes.get(vId);
  return type != null && type.getContent().equals(reactionsModel.getContent());
}
