private void bindHeaderView(BaseCell cell){
  if (cell != null && cell.isValid()) {
    View header=getViewFromRecycler(cell);
    if (header != null) {
      header.setId(R.id.TANGRAM_BANNER_HEADER_ID);
      LayoutParams lp=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
      lp.topMargin=cell.style.margin[Style.MARGIN_TOP_INDEX];
      lp.leftMargin=cell.style.margin[Style.MARGIN_LEFT_INDEX];
      lp.bottomMargin=cell.style.margin[Style.MARGIN_BOTTOM_INDEX];
      lp.rightMargin=cell.style.margin[Style.MARGIN_RIGHT_INDEX];
      addView(header,0,lp);
    }
  }
}
