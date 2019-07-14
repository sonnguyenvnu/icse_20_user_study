private static void setVisualProgressCompat(ProgressBar progressBar,int id,float progress){
  Drawable drawable=getVisualProgressDrawable(progressBar,id);
  if (drawable != null) {
    int level=(int)(progress * MAX_LEVEL);
    drawable.setLevel(level);
  }
 else {
    progressBar.invalidate();
  }
}
