public static void appendEmojies(@NonNull ReactionsModel reaction,@NonNull TextView thumbsUp,@NonNull TextView thumbsUpReaction,@NonNull TextView thumbsDown,@NonNull TextView thumbsDownReaction,@NonNull TextView hurray,@NonNull TextView hurrayReaction,@NonNull TextView sad,@NonNull TextView sadReaction,@NonNull TextView laugh,@NonNull TextView laughReaction,@NonNull TextView heart,@NonNull TextView heartReaction,@NonNull View reactionsList){
  SpannableBuilder spannableBuilder=SpannableBuilder.builder().append(CommentsHelper.getThumbsUp()).append(" ").append(String.valueOf(reaction.getPlusOne())).append("   ");
  thumbsUp.setText(spannableBuilder);
  thumbsUpReaction.setText(spannableBuilder);
  thumbsUpReaction.setVisibility(reaction.getPlusOne() > 0 ? View.VISIBLE : View.GONE);
  spannableBuilder=SpannableBuilder.builder().append(CommentsHelper.getThumbsDown()).append(" ").append(String.valueOf(reaction.getMinusOne())).append("   ");
  thumbsDown.setText(spannableBuilder);
  thumbsDownReaction.setText(spannableBuilder);
  thumbsDownReaction.setVisibility(reaction.getMinusOne() > 0 ? View.VISIBLE : View.GONE);
  spannableBuilder=SpannableBuilder.builder().append(CommentsHelper.getHooray()).append(" ").append(String.valueOf(reaction.getHooray())).append("   ");
  hurray.setText(spannableBuilder);
  hurrayReaction.setText(spannableBuilder);
  hurrayReaction.setVisibility(reaction.getHooray() > 0 ? View.VISIBLE : View.GONE);
  spannableBuilder=SpannableBuilder.builder().append(CommentsHelper.getSad()).append(" ").append(String.valueOf(reaction.getConfused())).append("   ");
  sad.setText(spannableBuilder);
  sadReaction.setText(spannableBuilder);
  sadReaction.setVisibility(reaction.getConfused() > 0 ? View.VISIBLE : View.GONE);
  spannableBuilder=SpannableBuilder.builder().append(CommentsHelper.getLaugh()).append(" ").append(String.valueOf(reaction.getLaugh())).append("   ");
  laugh.setText(spannableBuilder);
  laughReaction.setText(spannableBuilder);
  laughReaction.setVisibility(reaction.getLaugh() > 0 ? View.VISIBLE : View.GONE);
  spannableBuilder=SpannableBuilder.builder().append(CommentsHelper.getHeart()).append(" ").append(String.valueOf(reaction.getHeart()));
  heart.setText(spannableBuilder);
  heartReaction.setText(spannableBuilder);
  heartReaction.setVisibility(reaction.getHeart() > 0 ? View.VISIBLE : View.GONE);
  if (reaction.getPlusOne() > 0 || reaction.getMinusOne() > 0 || reaction.getLaugh() > 0 || reaction.getHooray() > 0 || reaction.getConfused() > 0 || reaction.getHeart() > 0) {
    reactionsList.setVisibility(View.VISIBLE);
    reactionsList.setTag(true);
  }
 else {
    reactionsList.setVisibility(View.GONE);
    reactionsList.setTag(false);
  }
}
