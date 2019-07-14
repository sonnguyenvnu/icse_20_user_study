@Override public void onChanged(){
  if (!changesAllowed) {
    throw new IllegalStateException("You cannot notify item changes directly. Call `requestModelBuild` instead.");
  }
}
