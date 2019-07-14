private static int dipToPixels(Resources res,int dips){
  float scale=res.getDisplayMetrics().density;
  return (int)(dips * scale + 0.5f);
}
