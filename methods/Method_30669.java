public void bind(String introduction){
  introduction=introduction.replaceFirst("^([ \\t\\xA0\\u1680\\u180e\\u2000-\\u200a\\u202f\\u205f\\u3000]*\\n)*","").replaceFirst("(\\n[ \\t\\xA0\\u1680\\u180e\\u2000-\\u200a\\u202f\\u205f\\u3000]*)*\\n?$","");
  if (!TextUtils.isEmpty(introduction)) {
    final String finalIntroduction=introduction;
    mTitleText.setOnClickListener(new OnClickListener(){
      @Override public void onClick(      View view){
        onCopyText(finalIntroduction);
      }
    }
);
    Drawable selectableItemBackground=ViewUtils.getDrawableFromAttrRes(R.attr.selectableItemBackground,getContext());
    boolean shouldSavePadding=Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP;
    if (shouldSavePadding) {
      ViewUtils.setBackgroundPreservingPadding(mTitleText,selectableItemBackground);
    }
 else {
      ViewCompat.setBackground(mTitleText,selectableItemBackground);
    }
    mContentText.setText(introduction);
  }
 else {
    mTitleText.setOnClickListener(null);
    mTitleText.setClickable(false);
    ViewCompat.setBackground(mTitleText,null);
    mContentText.setText(R.string.profile_introduction_empty);
  }
}
