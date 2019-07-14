@TargetApi(Build.VERSION_CODES.LOLLIPOP) private void init(){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    setOutlineProvider(new ViewOutlineProvider(){
      @TargetApi(Build.VERSION_CODES.LOLLIPOP) @Override public void getOutline(      View view,      Outline outline){
        int height=getHeight();
        int top=height > 0 ? height - computeVisibleAppBarHeight() : 0;
        outline.setRect(0,top,getWidth(),height);
      }
    }
);
  }
}
