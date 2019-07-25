public Dialog create(){
  final WebView webView=createWebView(mContext);
  webView.loadDataWithBaseURL(null,mLicensesText,"text/html","utf-8",null);
  final AlertDialog.Builder builder;
  if (mThemeResourceId != 0) {
    builder=new AlertDialog.Builder(new ContextThemeWrapper(mContext,mThemeResourceId));
  }
 else {
    builder=new AlertDialog.Builder(mContext);
  }
  builder.setTitle(mTitleText).setView(webView).setPositiveButton(mCloseText,(dialogInterface,i) -> dialogInterface.dismiss());
  final AlertDialog dialog=builder.create();
  dialog.setOnDismissListener(dialog1 -> {
    if (mOnDismissListener != null) {
      mOnDismissListener.onDismiss(dialog1);
    }
  }
);
  dialog.setOnShowListener(dialogInterface -> {
    if (mDividerColor != 0) {
      final int titleDividerId=mContext.getResources().getIdentifier("titleDivider","id","android");
      final View titleDivider=dialog.findViewById(titleDividerId);
      if (titleDivider != null) {
        titleDivider.setBackgroundColor(mDividerColor);
      }
    }
  }
);
  return dialog;
}
