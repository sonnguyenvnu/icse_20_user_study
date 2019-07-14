protected void reload(int position){
  remove(position);
  if (position == currentPosition) {
    selectFragment(position);
  }
}
