/** 
 * ???????
 */
private void getClipContent(){
  String clipContent=BaseTools.getClipContent();
  if (!TextUtils.isEmpty(clipContent)) {
    DialogBuild.showCustom(vpContent,clipContent,"??????",new DialogInterface.OnClickListener(){
      @Override public void onClick(      DialogInterface dialog,      int which){
        WebViewActivity.loadUrl(MainActivity.this,clipContent,"???..");
        BaseTools.clearClipboard();
      }
    }
);
  }
}
