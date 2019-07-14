private void appendViewToAllSpans(View view){
  for (int i=mSpanCount - 1; i >= 0; i--) {
    mSpans[i].appendToSpan(view);
  }
}
