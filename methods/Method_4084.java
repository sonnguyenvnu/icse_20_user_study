private void prependViewToAllSpans(View view){
  for (int i=mSpanCount - 1; i >= 0; i--) {
    mSpans[i].prependToSpan(view);
  }
}
