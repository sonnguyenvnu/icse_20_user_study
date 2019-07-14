public void fixFollowed(boolean followed){
  if (isFollowed != followed) {
    isFollowed=followed;
    if (isFollowed) {
      ++followerCount;
    }
 else {
      --followerCount;
    }
  }
}
