protected static float calculateProgress(int downloaded,int total){
  if (total > 0) {
    return (float)downloaded / total;
  }
 else {
    return 1 - (float)Math.exp(-downloaded / 5e4);
  }
}
