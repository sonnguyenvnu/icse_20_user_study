private static Layout createTextLayout(int widthSpec,TruncateAt ellipsize,boolean shouldIncludeFontPadding,int maxLines,float shadowRadius,float shadowDx,float shadowDy,int shadowColor,boolean isSingleLine,CharSequence text,int textColor,ColorStateList textColorStateList,int linkColor,int textSize,float extraSpacing,float spacingMultiplier,float letterSpacing,int textStyle,Typeface typeface,Alignment textAlignment,boolean glyphWarming,YogaDirection layoutDirection,int minEms,int maxEms,int minTextWidth,int maxTextWidth,float density,int breakStrategy,int hyphenationFrequency,int justificationMode,TextDirectionHeuristicCompat textDirection,float lineHeight){
  Layout newLayout;
  TextLayoutBuilder layoutBuilder=new TextLayoutBuilder();
  layoutBuilder.setShouldCacheLayout(false);
  @TextLayoutBuilder.MeasureMode final int textMeasureMode;
switch (SizeSpec.getMode(widthSpec)) {
case UNSPECIFIED:
    textMeasureMode=TextLayoutBuilder.MEASURE_MODE_UNSPECIFIED;
  break;
case EXACTLY:
textMeasureMode=TextLayoutBuilder.MEASURE_MODE_EXACTLY;
break;
case AT_MOST:
textMeasureMode=TextLayoutBuilder.MEASURE_MODE_AT_MOST;
break;
default :
throw new IllegalStateException("Unexpected size mode: " + SizeSpec.getMode(widthSpec));
}
layoutBuilder.setDensity(density).setEllipsize(ellipsize).setMaxLines(maxLines).setShadowLayer(shadowRadius,shadowDx,shadowDy,shadowColor).setSingleLine(isSingleLine).setText(text).setTextSize(textSize).setWidth(SizeSpec.getSize(widthSpec),textMeasureMode).setIncludeFontPadding(shouldIncludeFontPadding).setTextSpacingExtra(extraSpacing).setTextSpacingMultiplier(spacingMultiplier).setAlignment(textAlignment).setLinkColor(linkColor).setJustificationMode(justificationMode).setBreakStrategy(breakStrategy).setHyphenationFrequency(hyphenationFrequency);
if (lineHeight != Float.MAX_VALUE) {
layoutBuilder.setLineHeight(lineHeight);
}
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
layoutBuilder.setLetterSpacing(letterSpacing);
}
if (minEms != DEFAULT_EMS) {
layoutBuilder.setMinEms(minEms);
}
 else {
layoutBuilder.setMinWidth(minTextWidth);
}
if (maxEms != DEFAULT_EMS) {
layoutBuilder.setMaxEms(maxEms);
}
 else {
layoutBuilder.setMaxWidth(maxTextWidth);
}
if (textColor != 0) {
layoutBuilder.setTextColor(textColor);
}
 else {
layoutBuilder.setTextColor(textColorStateList);
}
if (!DEFAULT_TYPEFACE.equals(typeface)) {
layoutBuilder.setTypeface(typeface);
}
 else {
layoutBuilder.setTextStyle(textStyle);
}
if (textDirection != null) {
layoutBuilder.setTextDirection(textDirection);
}
 else {
layoutBuilder.setTextDirection(layoutDirection == YogaDirection.RTL ? TextDirectionHeuristicsCompat.FIRSTSTRONG_RTL : TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR);
}
newLayout=layoutBuilder.build();
if (glyphWarming) {
TextureWarmer.getInstance().warmLayout(newLayout);
}
return newLayout;
}
