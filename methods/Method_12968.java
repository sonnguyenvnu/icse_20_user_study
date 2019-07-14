/** 
 * ??????lib??assets?? WebViewJavascriptBridge.js
 * @param view webview
 * @param path ??
 */
public static void webViewLoadLocalJs(WebView view,String path){
  String jsContent=assetFile2Str(view.getContext(),path);
  view.loadUrl("javascript:" + jsContent);
}
