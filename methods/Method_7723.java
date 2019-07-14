private static void calcBackgroundColor(Drawable drawable,int save){
  if (save != 2) {
    int[] result=AndroidUtilities.calcDrawableColor(drawable);
    serviceMessageColor=serviceMessageColorBackup=result[0];
    serviceSelectedMessageColor=serviceSelectedMessageColorBackup=result[1];
    serviceMessage2Color=result[2];
    serviceSelectedMessage2Color=result[3];
  }
}
