private void addReactionCount(View v){
  if (adapter != null) {
    ReviewCommentModel comment=(ReviewCommentModel)adapter.getItem(getAdapterPosition());
    if (comment != null) {
      boolean isReacted=reactionsCallback == null || reactionsCallback.isPreviouslyReacted(comment.getId(),v.getId());
      ReactionsModel reactionsModel=comment.getReactions() != null ? comment.getReactions() : new ReactionsModel();
switch (v.getId()) {
case R.id.heart:
case R.id.heartReaction:
        reactionsModel.setHeart(!isReacted ? reactionsModel.getHeart() + 1 : reactionsModel.getHeart() - 1);
      break;
case R.id.sad:
case R.id.sadReaction:
    reactionsModel.setConfused(!isReacted ? reactionsModel.getConfused() + 1 : reactionsModel.getConfused() - 1);
  break;
case R.id.thumbsDown:
case R.id.thumbsDownReaction:
reactionsModel.setMinusOne(!isReacted ? reactionsModel.getMinusOne() + 1 : reactionsModel.getMinusOne() - 1);
break;
case R.id.thumbsUp:
case R.id.thumbsUpReaction:
reactionsModel.setPlusOne(!isReacted ? reactionsModel.getPlusOne() + 1 : reactionsModel.getPlusOne() - 1);
break;
case R.id.laugh:
case R.id.laughReaction:
reactionsModel.setLaugh(!isReacted ? reactionsModel.getLaugh() + 1 : reactionsModel.getLaugh() - 1);
break;
case R.id.hurray:
case R.id.hurrayReaction:
reactionsModel.setHooray(!isReacted ? reactionsModel.getHooray() + 1 : reactionsModel.getHooray() - 1);
break;
}
comment.setReactions(reactionsModel);
appendEmojies(reactionsModel);
}
}
}
