public void setLoading(boolean value){
  progressBar.setVisibility(value ? VISIBLE : INVISIBLE);
  textView.setVisibility(value ? INVISIBLE : VISIBLE);
}
