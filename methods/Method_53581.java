public void updateTags(CharSequence... tags){
  clearTags();
  for (  CharSequence tag : tags) {
    addTag(tag);
  }
}
