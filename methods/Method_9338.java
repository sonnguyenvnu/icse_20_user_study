public void updateAdapters(){
  if (photoVideoAdapter != null) {
    photoVideoAdapter.notifyDataSetChanged();
  }
  if (documentsAdapter != null) {
    documentsAdapter.notifyDataSetChanged();
  }
  if (voiceAdapter != null) {
    voiceAdapter.notifyDataSetChanged();
  }
  if (linksAdapter != null) {
    linksAdapter.notifyDataSetChanged();
  }
  if (audioAdapter != null) {
    audioAdapter.notifyDataSetChanged();
  }
}
