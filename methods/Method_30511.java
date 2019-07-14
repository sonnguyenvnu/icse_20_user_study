public void fixLiked(boolean liked){
  if (isLiked != liked) {
    isLiked=liked;
    if (isLiked) {
      ++likeCount;
    }
 else {
      --likeCount;
    }
  }
}
