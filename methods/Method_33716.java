/** 
 * ???????????
 * @param width ????
 * @param bili  ????
 * @param type  1:?? LinearLayout 2??? RelativeLayout
 */
public static void formatHeight(View imageView,int width,float bili,int type){
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
    FrameLayout.LayoutParams lp=new FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,height);
    imageView.setLayoutParams(lp);
  }
}
