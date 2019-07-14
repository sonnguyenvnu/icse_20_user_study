private void buildRemoteViews(View view,RemoteViews remoteViews,int width,int height){
  Bitmap bitmap=getBitmapFromView(view);
  remoteViews.setImageViewBitmap(R.id.imageView,bitmap);
  adjustRemoteViewsPadding(remoteViews,view,width,height);
  PendingIntent onClickIntent=getOnClickPendingIntent(context);
  if (onClickIntent != null)   remoteViews.setOnClickPendingIntent(R.id.button,onClickIntent);
}
