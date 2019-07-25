@Override public void dispose(){
  INSTANCE.removeResolver(myScopeResolver);
  INSTANCE=null;
}
