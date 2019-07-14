/** 
 * ??????????
 * @param position
 * @param fsb
 */
@SuppressLint("InflateParams") private void addItem(final boolean isMoreButton,final int position,final Menu fsb){
  if (isMoreButton == false) {
    if (position < 0 || fsb == null || StringUtil.isNotEmpty(fsb.getName(),true) == false || fsb.getImageRes() <= 0) {
      Log.e(TAG,"addItem isMoreButton == false >> position < 0 || fsb == null " + "|| StringUtil.isNotEmpty(fsb.getName(), true) == false " + "|| fsb.getImageRes() <= 0 >> return;");
      return;
    }
    ;
  }
  LinearLayout ll=(LinearLayout)inflater.inflate(R.layout.icon_name_item,null);
  ImageView iv=(ImageView)ll.findViewById(R.id.ivIconNameIcon);
  TextView tv=(TextView)ll.findViewById(R.id.tvIconNameName);
  try {
    iv.setImageResource(isMoreButton ? R.drawable.up2_light : fsb.getImageRes());
  }
 catch (  Exception e) {
    Log.e(TAG,"addItem try {" + " iv.setImageResource(fsb.getImageRes()); " + e.getMessage() + ">> return;");
    return;
  }
  tv.setText(isMoreButton ? "??" : "" + fsb.getName());
  ll.setPadding((int)getDimension(R.dimen.common_item_left_tv_padding),0,(int)getDimension(R.dimen.common_item_right_img_padding_right),0);
  ll.setOnClickListener(new OnClickListener(){
    @Override public void onClick(    View v){
      if (isMoreButton) {
        CommonUtil.toActivity(context,BottomMenuWindow.createIntent(context,moreMenuNameList,moreMenuIntentCodeList).putExtra(BottomMenuWindow.INTENT_TITLE,"??"),toBottomMenuWindowRequestCode,false);
      }
 else {
        onBottomMenuItemClickListener.onBottomMenuItemClick(fsb.getIntentCode());
      }
    }
  }
);
  llBottomMenuViewMainItemContainer.addView(ll,position);
}
