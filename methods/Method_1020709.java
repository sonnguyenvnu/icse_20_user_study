@Override public void dispose(){
  SubscriptionHelper.cancel(this);
  removeSelf();
}
