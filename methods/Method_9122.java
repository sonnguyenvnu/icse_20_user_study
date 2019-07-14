@Override public void updateMeasureState(TextPaint p){
  p.setTypeface(Typeface.MONOSPACE);
  p.setTextSize(AndroidUtilities.dp(SharedConfig.fontSize - 1));
  p.setFlags(p.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
}
