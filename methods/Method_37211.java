private void bindFooterView(BaseCell cell){
  if (cell != null && cell.isValid()) {
    View footer=getFooterViewFromRecycler(cell);
    if (footer != null) {
      ViewGroup.LayoutParams lp=footer.getLayoutParams();
      if (lp == null || !(lp instanceof LayoutParams)) {
        lp=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
      }
      ((LayoutParams)lp).topMargin=cell.style.margin[Style.MARGIN_TOP_INDEX];
      ((LayoutParams)lp).leftMargin=cell.style.margin[Style.MARGIN_LEFT_INDEX];
      ((LayoutParams)lp).bottomMargin=cell.style.margin[Style.MARGIN_BOTTOM_INDEX];
      ((LayoutParams)lp).rightMargin=cell.style.margin[Style.MARGIN_RIGHT_INDEX];
      addView(footer,lp);
    }
  }
}
