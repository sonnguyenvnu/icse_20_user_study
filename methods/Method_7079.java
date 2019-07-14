public void setAnimationInProgress(boolean value){
  animationInProgress=value;
  if (!animationInProgress && !delayedPosts.isEmpty()) {
    for (int a=0; a < delayedPosts.size(); a++) {
      DelayedPost delayedPost=delayedPosts.get(a);
      postNotificationNameInternal(delayedPost.id,true,delayedPost.args);
    }
    delayedPosts.clear();
  }
}
