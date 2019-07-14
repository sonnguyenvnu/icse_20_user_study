@Override public int getItemViewType(int position){
  if (keywordResults != null && !keywordResults.isEmpty()) {
    return 1;
  }
  return 0;
}
