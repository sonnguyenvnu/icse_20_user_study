private void adjustRemoteViewsPadding(RemoteViews remoteViews,View view,int width,int height){
  int imageWidth=view.getMeasuredWidth();
  int imageHeight=view.getMeasuredHeight();
  int p[]=calculatePadding(width,height,imageWidth,imageHeight);
  remoteViews.setViewPadding(R.id.buttonOverlay,p[0],p[1],p[2],p[3]);
}
