public Bitmap.Config getImageConfig(){
switch (imageConfig) {
case 1:
    return Bitmap.Config.ALPHA_8;
case 2:
  return Bitmap.Config.ARGB_4444;
case 3:
return Bitmap.Config.ARGB_8888;
case 4:
return Bitmap.Config.RGB_565;
}
return Bitmap.Config.ARGB_8888;
}
