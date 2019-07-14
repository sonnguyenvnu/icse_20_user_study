public boolean isFriendFollow(){
  return activity() != null && activity().category().equals(com.kickstarter.models.Activity.CATEGORY_FOLLOW);
}
