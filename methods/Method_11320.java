@Override public View makeView(){
  TextView t=new TextView(mContext);
  t.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
  t.setMaxLines(1);
  t.setPadding(mPadding,mPadding,mPadding,mPadding);
  t.setTextColor(textColor);
  t.setTextSize(mTextSize);
  t.setClickable(true);
  t.setOnClickListener(new OnClickListener(){
    @Override public void onClick(    View v){
      if (itemClickListener != null && textList.size() > 0 && currentId != -1) {
        itemClickListener.onItemClick(currentId % textList.size());
      }
    }
  }
);
  return t;
}
