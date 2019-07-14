@Override public void loadData(Priority priority,DataCallback<? super Bitmap> callback){
  inputStream=CloudUtil.getThumbnailInputStreamForCloud(context,path);
  BitmapFactory.Options options=new BitmapFactory.Options();
  options.outWidth=width;
  options.outHeight=height;
  Bitmap drawable=BitmapFactory.decodeStream(inputStream,null,options);
  callback.onDataReady(drawable);
}
