@Override protected void disable(Principal principal){
  this.category.getFeeds().forEach(feed -> enableOnly(principal,streamAllRoleMemberships(feed),feed.getAllowedActions()));
}
