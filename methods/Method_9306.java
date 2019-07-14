private Bitmap createUserBitmap(LiveLocation liveLocation){
  Bitmap result=null;
  try {
    TLRPC.FileLocation photo=null;
    if (liveLocation.user != null && liveLocation.user.photo != null) {
      photo=liveLocation.user.photo.photo_small;
    }
 else     if (liveLocation.chat != null && liveLocation.chat.photo != null) {
      photo=liveLocation.chat.photo.photo_small;
    }
    result=Bitmap.createBitmap(AndroidUtilities.dp(62),AndroidUtilities.dp(76),Bitmap.Config.ARGB_8888);
    result.eraseColor(Color.TRANSPARENT);
    Canvas canvas=new Canvas(result);
    Drawable drawable=ApplicationLoader.applicationContext.getResources().getDrawable(R.drawable.livepin);
    drawable.setBounds(0,0,AndroidUtilities.dp(62),AndroidUtilities.dp(76));
    drawable.draw(canvas);
    Paint roundPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    RectF bitmapRect=new RectF();
    canvas.save();
    if (photo != null) {
      File path=FileLoader.getPathToAttach(photo,true);
      Bitmap bitmap=BitmapFactory.decodeFile(path.toString());
      if (bitmap != null) {
        BitmapShader shader=new BitmapShader(bitmap,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
        Matrix matrix=new Matrix();
        float scale=AndroidUtilities.dp(52) / (float)bitmap.getWidth();
        matrix.postTranslate(AndroidUtilities.dp(5),AndroidUtilities.dp(5));
        matrix.postScale(scale,scale);
        roundPaint.setShader(shader);
        shader.setLocalMatrix(matrix);
        bitmapRect.set(AndroidUtilities.dp(5),AndroidUtilities.dp(5),AndroidUtilities.dp(52 + 5),AndroidUtilities.dp(52 + 5));
        canvas.drawRoundRect(bitmapRect,AndroidUtilities.dp(26),AndroidUtilities.dp(26),roundPaint);
      }
    }
 else {
      AvatarDrawable avatarDrawable=new AvatarDrawable();
      if (liveLocation.user != null) {
        avatarDrawable.setInfo(liveLocation.user);
      }
 else       if (liveLocation.chat != null) {
        avatarDrawable.setInfo(liveLocation.chat);
      }
      canvas.translate(AndroidUtilities.dp(5),AndroidUtilities.dp(5));
      avatarDrawable.setBounds(0,0,AndroidUtilities.dp(52.2f),AndroidUtilities.dp(52.2f));
      avatarDrawable.draw(canvas);
    }
    canvas.restore();
    try {
      canvas.setBitmap(null);
    }
 catch (    Exception e) {
    }
  }
 catch (  Throwable e) {
    FileLog.e(e);
  }
  return result;
}
