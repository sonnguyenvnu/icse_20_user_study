public static void handleReactions(@NonNull Context context,@NonNull String login,@NonNull String repoId,@IdRes int id,long commentId,boolean commit,boolean isDelete,boolean isEnterprise){
  ReactionTypes type=null;
switch (id) {
case R.id.heart:
    type=ReactionTypes.HEART;
  break;
case R.id.sad:
type=ReactionTypes.CONFUSED;
break;
case R.id.thumbsDown:
type=ReactionTypes.MINUS_ONE;
break;
case R.id.thumbsUp:
type=ReactionTypes.PLUS_ONE;
break;
case R.id.laugh:
type=ReactionTypes.LAUGH;
break;
case R.id.hurray:
type=ReactionTypes.HOORAY;
break;
}
if (type != null) {
ReactionService.start(context,login,repoId,commentId,type,commit,isDelete,isEnterprise);
}
}
