private void _XXXXX_(LifecycleState expected){
  checkState(expected == lifecycleState,"Unexpected state " + lifecycleState + ", expected to be "+ expected);
}