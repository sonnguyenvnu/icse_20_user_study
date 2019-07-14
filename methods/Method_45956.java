@Override public boolean isSubscribed(){
  return respondRegistries == null || respondRegistries.getCount() <= 0;
}
