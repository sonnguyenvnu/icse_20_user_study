private void bindFooterView(BaseCell cell){
  if (cell.isValid()) {
    View footer=getViewFromRecycler(cell);
    if (footer != null) {
      footer.setId(R.id.TANGRAM_BANNER_FOOTER_ID);
      LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
      lp.topMargin=cell.style.margin[Style.MARGIN_TOP_INDEX];
      lp.leftMargin=cell.style.margin[Style.MARGIN_LEFT_INDEX];
      lp.bottomMargin=cell.style.margin[Style.MARGIN_BOTTOM_INDEX];
      lp.rightMargin=cell.style.margin[Style.MARGIN_RIGHT_INDEX];
      addView(footer,lp);
    }
  }
}
