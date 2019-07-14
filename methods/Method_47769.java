public static int colorToPaletteIndex(Context context,int color){
  StyledResources res=new StyledResources(context);
  int[] palette=res.getPalette();
  for (int k=0; k < palette.length; k++)   if (palette[k] == color)   return k;
  return -1;
}
