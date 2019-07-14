@DoNotStrip private static Bitmap originalDecodeResourceStream(Resources res,TypedValue value,InputStream is,Rect pad,BitmapFactory.Options opts){
  return BitmapFactory.decodeResourceStream(res,value,is,pad,opts);
}
