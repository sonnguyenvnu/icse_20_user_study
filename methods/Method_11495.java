/** 
 * ????????,?? 1.???????:????tab?????? 2.???????(WRAP_CONTENT):????tab??????,???????????????,????bottomPadding????topPadding
 */
public void setMsgMargin(int position,float leftPadding,float bottomPadding){
  if (position >= mTabCount) {
    position=mTabCount - 1;
  }
  View tabView=mTabsContainer.getChildAt(position);
  MsgView tipView=(MsgView)tabView.findViewById(R.id.rtv_msg_tip);
  if (tipView != null) {
    TextView tv_tab_title=(TextView)tabView.findViewById(R.id.tv_tab_title);
    mTextPaint.setTextSize(mTextsize);
    float textWidth=mTextPaint.measureText(tv_tab_title.getText().toString());
    float textHeight=mTextPaint.descent() - mTextPaint.ascent();
    MarginLayoutParams lp=(MarginLayoutParams)tipView.getLayoutParams();
    lp.leftMargin=dp2px(leftPadding);
    lp.topMargin=mHeight > 0 ? (int)(mHeight - textHeight) / 2 - dp2px(bottomPadding) : dp2px(bottomPadding);
    tipView.setLayoutParams(lp);
  }
}
