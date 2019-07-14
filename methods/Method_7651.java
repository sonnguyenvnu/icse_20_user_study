@Override protected void onCreate(Bundle savedInstanceState){
  super.onCreate(savedInstanceState);
  Window window=getWindow();
  window.setWindowAnimations(R.style.DialogNoAnimation);
  setContentView(container,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
  if (containerView == null) {
    containerView=new FrameLayout(getContext()){
      @Override public boolean hasOverlappingRendering(){
        return false;
      }
      @Override public void setTranslationY(      float translationY){
        super.setTranslationY(translationY);
        onContainerTranslationYChanged(translationY);
      }
    }
;
    containerView.setBackgroundDrawable(shadowDrawable);
    containerView.setPadding(backgroundPaddingLeft,(applyTopPadding ? AndroidUtilities.dp(8) : 0) + backgroundPaddingTop - 1,backgroundPaddingLeft,(applyBottomPadding ? AndroidUtilities.dp(8) : 0));
  }
  containerView.setVisibility(View.INVISIBLE);
  container.addView(containerView,0,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,Gravity.BOTTOM));
  int topOffset=0;
  if (title != null) {
    titleView=new TextView(getContext());
    titleView.setLines(1);
    titleView.setSingleLine(true);
    titleView.setText(title);
    titleView.setTextColor(Theme.getColor(Theme.key_dialogTextGray2));
    titleView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
    titleView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
    titleView.setPadding(AndroidUtilities.dp(16),0,AndroidUtilities.dp(16),AndroidUtilities.dp(8));
    titleView.setGravity(Gravity.CENTER_VERTICAL);
    containerView.addView(titleView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,48));
    titleView.setOnTouchListener((v,event) -> true);
    topOffset+=48;
  }
  if (customView != null) {
    if (customView.getParent() != null) {
      ViewGroup viewGroup=(ViewGroup)customView.getParent();
      viewGroup.removeView(customView);
    }
    containerView.addView(customView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,Gravity.LEFT | Gravity.TOP,0,topOffset,0,0));
  }
 else {
    if (items != null) {
      FrameLayout rowLayout=null;
      int lastRowLayoutNum=0;
      for (int a=0; a < items.length; a++) {
        if (items[a] == null) {
          continue;
        }
        BottomSheetCell cell=new BottomSheetCell(getContext(),0);
        cell.setTextAndIcon(items[a],itemIcons != null ? itemIcons[a] : 0);
        containerView.addView(cell,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,48,Gravity.LEFT | Gravity.TOP,0,topOffset,0,0));
        topOffset+=48;
        cell.setTag(a);
        cell.setOnClickListener(v -> dismissWithButtonClick((Integer)v.getTag()));
        itemViews.add(cell);
      }
    }
  }
  WindowManager.LayoutParams params=window.getAttributes();
  params.width=ViewGroup.LayoutParams.MATCH_PARENT;
  params.gravity=Gravity.TOP | Gravity.LEFT;
  params.dimAmount=0;
  params.flags&=~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
  if (focusable) {
    params.softInputMode=WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;
  }
 else {
    params.flags|=WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
  }
  if (isFullscreen) {
    if (Build.VERSION.SDK_INT >= 21) {
      params.flags|=WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR | WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;
    }
    params.flags|=WindowManager.LayoutParams.FLAG_FULLSCREEN;
    container.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_FULLSCREEN);
  }
  params.height=ViewGroup.LayoutParams.MATCH_PARENT;
  if (Build.VERSION.SDK_INT >= 28) {
    params.layoutInDisplayCutoutMode=WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
  }
  window.setAttributes(params);
}
