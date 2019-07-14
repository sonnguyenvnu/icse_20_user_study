@Override public void restoreFromBundle(@NonNull Bundle bundle){
  List<String> savedNames=bundle.getStringArrayList(KEY_WAIT_FOR_TRANSITION_NAMES);
  if (savedNames != null) {
    names.addAll(savedNames);
  }
}
