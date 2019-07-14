protected void renderLayout(BaseCell cell,View view){
  if (cell.style != null) {
    ViewGroup.LayoutParams lp=view.getLayoutParams();
    if (lp == null || !(lp instanceof VirtualLayoutManager.LayoutParams)) {
      if (lp == null) {
        lp=new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
      }
 else {
        lp=new VirtualLayoutManager.LayoutParams(lp.width,lp.height);
      }
      view.setLayoutParams(lp);
    }
    if (lp instanceof VirtualLayoutManager.LayoutParams) {
      VirtualLayoutManager.LayoutParams params=(VirtualLayoutManager.LayoutParams)lp;
      if (cell.style.height >= 0) {
        params.storeOriginHeight();
        params.height=cell.style.height;
      }
 else {
        params.restoreOriginHeight();
      }
      if (cell.style.width >= 0) {
        params.storeOriginWidth();
        params.width=cell.style.width;
      }
 else {
        params.restoreOriginWidth();
      }
      params.mAspectRatio=cell.style.aspectRatio;
      params.zIndex=cell.style.zIndex;
      if (params.zIndex == 0) {
        if (cell.parent != null && cell.parent.style != null) {
          params.zIndex=cell.parent.style.zIndex;
        }
      }
      if (VERSION.SDK_INT >= 21) {
        view.setZ(params.zIndex);
      }
    }
 else {
      if (cell.style.height >= 0) {
        lp.height=cell.style.height;
      }
      if (cell.style.width >= 0) {
        lp.width=cell.style.width;
      }
    }
    if (lp instanceof ViewGroup.MarginLayoutParams) {
      ViewGroup.MarginLayoutParams layoutParams=(ViewGroup.MarginLayoutParams)lp;
      layoutParams.topMargin=cell.style.margin[MARGIN_TOP_INDEX];
      layoutParams.leftMargin=cell.style.margin[MARGIN_LEFT_INDEX];
      layoutParams.bottomMargin=cell.style.margin[MARGIN_BOTTOM_INDEX];
      layoutParams.rightMargin=cell.style.margin[MARGIN_RIGHT_INDEX];
    }
    view.setTranslationX(0);
    view.setTranslationY(0);
  }
}
