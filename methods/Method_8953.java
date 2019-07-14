public static Rect getPipRect(float aspectRatio){
  SharedPreferences preferences=ApplicationLoader.applicationContext.getSharedPreferences("pipconfig",Context.MODE_PRIVATE);
  int sidex=preferences.getInt("sidex",1);
  int sidey=preferences.getInt("sidey",0);
  float px=preferences.getFloat("px",0);
  float py=preferences.getFloat("py",0);
  int videoWidth;
  int videoHeight;
  if (aspectRatio > 1) {
    videoWidth=AndroidUtilities.dp(192);
    videoHeight=(int)(videoWidth / aspectRatio);
  }
 else {
    videoHeight=AndroidUtilities.dp(192);
    videoWidth=(int)(videoHeight * aspectRatio);
  }
  return new Rect(getSideCoord(true,sidex,px,videoWidth),getSideCoord(false,sidey,py,videoHeight),videoWidth,videoHeight);
}
