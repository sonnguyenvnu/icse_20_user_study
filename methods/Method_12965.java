public static String parseFunctionName(String jsUrl){
  return jsUrl.replace("javascript:WebViewJavascriptBridge.","").replaceAll("\\(.*\\);","");
}
