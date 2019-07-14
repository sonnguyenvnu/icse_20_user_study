public String getResolution(){
  if (width != -1 && -1 != height)   return String.format(Locale.getDefault(),"%dx%d",width,height);
 else   return "¿x?";
}
