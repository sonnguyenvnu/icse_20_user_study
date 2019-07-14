private void loadImageClickJS(){
  webView.loadUrl("javascript:(function(){" + "var objs = document.getElementsByTagName(\"img\");" + "for(var i=0;i<objs.length;i++)" + "{" + "objs[i].onclick=function(){window.injectedObject.imageClick(this.getAttribute(\"src\"),this.getAttribute(\"has_link\"));}" + "}" + "})()");
}
