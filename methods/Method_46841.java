private void cleanSpans(){
  nodes.clear();
  mCurrent=-1;
  mLine=0;
  BackgroundColorSpan[] colorSpans=mInput.getText().getSpans(0,mInput.length(),BackgroundColorSpan.class);
  for (  BackgroundColorSpan colorSpan : colorSpans) {
    mInput.getText().removeSpan(colorSpan);
  }
}
