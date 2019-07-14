/** 
 * ????????
 * @param bili         ????
 * @param type         1:?? LinearLayout 2??? RelativeLayout
 * @param marginLR     ???dp
 * @param marginTop    ???dp
 * @param marginBottom ???dp
 */
public static void formatHeight(View imageView,float bili,int type,int marginLR,int marginTop,int marginBottom){
  WindowManager wm=(WindowManager)CloudReaderApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
  int width=wm.getDefaultDisplay().getWidth();
  int height=(int)(width / bili);
  if (type == 1) {
    LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,height);
    imageView.setLayoutParams(lp);
  }
 else   if (type == 2) {
    RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,height);
    imageView.setLayoutParams(lp);
  }
 else {
    FrameLayout.LayoutParams lp=new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,height);
    imageView.setLayoutParams(lp);
  }
  ViewGroup.MarginLayoutParams layoutParams=(ViewGroup.MarginLayoutParams)imageView.getLayoutParams();
  layoutParams.setMargins(dip2px(marginLR),dip2px(marginTop),dip2px(marginLR),dip2px(marginBottom));
}
