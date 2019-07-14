private void loadTextClickJS(){
  webView.loadUrl("javascript:(function(){" + "var objs =document.getElementsByTagName(\"a\");" + "for(var i=0;i<objs.length;i++)" + "{" + "objs[i].onclick=function(){" + "window.injectedObject.textClick(this.getAttribute(\"type\"),this.getAttribute(\"item_pk\"));}" + "}" + "})()");
}
