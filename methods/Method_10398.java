protected final void debugHeaders(String TAG,Header[] headers){
  if (headers != null) {
    Log.d(TAG,"Return Headers:");
    StringBuilder builder=new StringBuilder();
    for (    Header h : headers) {
      String _h=String.format(Locale.US,"%s : %s",h.getName(),h.getValue());
      Log.d(TAG,_h);
      builder.append(_h);
      builder.append("\n");
    }
    addView(getColoredView(YELLOW,builder.toString()));
  }
}
