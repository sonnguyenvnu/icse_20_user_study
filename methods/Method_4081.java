private void attachViewToSpans(View view,LayoutParams lp,LayoutState layoutState){
  if (layoutState.mLayoutDirection == LayoutState.LAYOUT_END) {
    if (lp.mFullSpan) {
      appendViewToAllSpans(view);
    }
 else {
      lp.mSpan.appendToSpan(view);
    }
  }
 else {
    if (lp.mFullSpan) {
      prependViewToAllSpans(view);
    }
 else {
      lp.mSpan.prependToSpan(view);
    }
  }
}
