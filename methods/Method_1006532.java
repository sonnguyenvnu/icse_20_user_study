@Override public IRouter anim(@AnimRes int enterAnim,@AnimRes int exitAnim){
  mRouteRequest.setEnterAnim(enterAnim);
  mRouteRequest.setExitAnim(exitAnim);
  return this;
}
