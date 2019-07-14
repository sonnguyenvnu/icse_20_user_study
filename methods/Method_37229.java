private void bindFooterView(BaseCell cell){
  if (cell != null && cell.isValid()) {
    View footer=getViewFromRecycler(cell);
    if (footer != null) {
      footer.setId(R.id.TANGRAM_BANNER_FOOTER_ID);
      LayoutParams lp=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
      lp.topMargin=cell.style.margin[Style.MARGIN_TOP_INDEX];
      lp.leftMargin=cell.style.margin[Style.MARGIN_LEFT_INDEX];
      lp.bottomMargin=cell.style.margin[Style.MARGIN_BOTTOM_INDEX];
      lp.rightMargin=cell.style.margin[Style.MARGIN_RIGHT_INDEX];
      addView(footer,lp);
    }
  }
}
