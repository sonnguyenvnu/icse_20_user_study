private void initializeUserAgents(){
  mDefaultUserAgent=mWebView.getSettings().getUserAgentString();
  mDesktopUserAgent=mDefaultUserAgent.replaceFirst("\\(Linux;.*?\\)","(X11; Linux x86_64)").replace("Mobile Safari/","Safari/");
}
