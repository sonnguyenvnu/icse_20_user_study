@Override public void setSupportActionBar(@Nullable Toolbar toolbar){
  super.setSupportActionBar(toolbar);
  TransitionUtils.setupTransitionForAppBar(this);
}
