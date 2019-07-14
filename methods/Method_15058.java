/** 
 * ??????????Activity???INTENT_TITLE??????initView???
 * @param tvTitle
 * @return tvTitle ??tvTitle??????????? tvTitle = autoSetTitle((TextView) findViewById(titleResId));
 * @must ?UI?????
 */
protected TextView autoSetTitle(TextView tvTitle){
  if (tvTitle != null) {
    String title=getIntent().getStringExtra(INTENT_TITLE);
    if (StringUtil.isNotEmpty(title,false)) {
      tvTitle.setText(StringUtil.getString(title));
    }
    if (pbBaseTitle != null) {
      tvTitle.setOnClickListener(new OnClickListener(){
        @Override public void onClick(        View v){
          if (isShowingProgress() == false) {
            initData();
          }
        }
      }
);
    }
  }
  return tvTitle;
}
