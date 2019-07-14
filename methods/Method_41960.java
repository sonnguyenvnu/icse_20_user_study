private void restoreState(@NonNull Bundle savedInstance){
  fragmentMode=savedInstance.getInt(SAVE_FRAGMENT_MODE,FragmentMode.MODE_ALBUMS);
}
