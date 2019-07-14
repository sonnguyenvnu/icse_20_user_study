/** 
 * ????????
 */
private boolean handleLongImage(){
  final WebView.HitTestResult hitTestResult=webView.getHitTestResult();
  if (hitTestResult.getType() == WebView.HitTestResult.IMAGE_TYPE || hitTestResult.getType() == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {
    new AlertDialog.Builder(WebViewActivity.this).setItems(new String[]{"????","???????"},new DialogInterface.OnClickListener(){
      @Override public void onClick(      DialogInterface dialog,      int which){
        String picUrl=hitTestResult.getExtra();
switch (which) {
case 0:
          ViewBigImageActivity.start(WebViewActivity.this,picUrl,picUrl);
        break;
case 1:
      if (!PermissionHandler.isHandlePermission(WebViewActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
        return;
      }
    RxSaveImage.saveImageToGallery(WebViewActivity.this,picUrl,picUrl);
  break;
default :
break;
}
}
}
).show();
return true;
}
return false;
}
