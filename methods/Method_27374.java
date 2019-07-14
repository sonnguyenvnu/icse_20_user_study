public static String getEmoji(@NonNull ReactionTypes reactionTypes){
switch (reactionTypes) {
case HEART:
    return getHeart();
case HOORAY:
  return getHooray();
case PLUS_ONE:
return getThumbsUp();
case MINUS_ONE:
return getThumbsDown();
case CONFUSED:
return getSad();
case LAUGH:
return getLaugh();
default :
return getThumbsUp();
}
}
