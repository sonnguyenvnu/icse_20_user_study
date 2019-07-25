private void ensure(int position){
  if (position >= 0) {
    ensureInternal(position,positive);
  }
 else {
    ensureInternal(-position - 1,negative);
  }
}
