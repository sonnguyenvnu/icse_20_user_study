@OnBoundsDefined static void onBoundsDefined(ComponentContext c,ComponentLayout layout,@Prop(resType=ResType.STRING) CharSequence text,@Prop(optional=true) TruncateAt ellipsize,@Prop(optional=true,resType=ResType.BOOL) boolean shouldIncludeFontPadding,@Prop(optional=true,resType=ResType.INT) int maxLines,@Prop(optional=true,resType=ResType.INT) int minEms,@Prop(optional=true,resType=ResType.INT) int maxEms,@Prop(optional=true,resType=ResType.DIMEN_SIZE) int minTextWidth,@Prop(optional=true,resType=ResType.DIMEN_SIZE) int maxTextWidth,@Prop(optional=true,resType=ResType.DIMEN_OFFSET) float shadowRadius,@Prop(optional=true,resType=ResType.DIMEN_OFFSET) float shadowDx,@Prop(optional=true,resType=ResType.DIMEN_OFFSET) float shadowDy,@Prop(optional=true,resType=ResType.COLOR) int shadowColor,@Prop(optional=true,resType=ResType.BOOL) boolean isSingleLine,@Prop(optional=true,resType=ResType.COLOR) int textColor,@Prop(optional=true) ColorStateList textColorStateList,@Prop(optional=true,resType=ResType.COLOR) int linkColor,@Prop(optional=true,resType=ResType.DIMEN_TEXT) int textSize,@Prop(optional=true,resType=ResType.DIMEN_OFFSET) float extraSpacing,@Prop(optional=true,resType=ResType.FLOAT) float spacingMultiplier,@Prop(optional=true,resType=ResType.FLOAT) float letterSpacing,@Prop(optional=true) VerticalGravity verticalGravity,@Prop(optional=true) int textStyle,@Prop(optional=true) Typeface typeface,@Prop(optional=true) Alignment textAlignment,@Prop(optional=true) int breakStrategy,@Prop(optional=true) int hyphenationFrequency,@Prop(optional=true) boolean glyphWarming,@Prop(optional=true) TextDirectionHeuristicCompat textDirection,@Prop(optional=true,resType=ResType.STRING) CharSequence customEllipsisText,@Prop(optional=true,resType=ResType.DIMEN_TEXT) float lineHeight,@FromMeasure Layout measureLayout,@FromMeasure Integer measuredWidth,@FromMeasure Integer measuredHeight,Output<CharSequence> processedText,Output<Layout> textLayout,Output<Float> textLayoutTranslationY,Output<ClickableSpan[]> clickableSpans,Output<ImageSpan[]> imageSpans){
  processedText.set(text);
  if (TextUtils.isEmpty(text)) {
    return;
  }
  final float layoutWidth=layout.getWidth() - layout.getPaddingLeft() - layout.getPaddingRight();
  final float layoutHeight=layout.getHeight() - layout.getPaddingTop() - layout.getPaddingBottom();
  if (measureLayout != null && measuredWidth == layoutWidth && measuredHeight == layoutHeight) {
    textLayout.set(measureLayout);
  }
 else {
    textLayout.set(createTextLayout(SizeSpec.makeSizeSpec((int)layoutWidth,EXACTLY),ellipsize,shouldIncludeFontPadding,maxLines,shadowRadius,shadowDx,shadowDy,shadowColor,isSingleLine,text,textColor,textColorStateList,linkColor,textSize,extraSpacing,spacingMultiplier,letterSpacing,textStyle,typeface,textAlignment,glyphWarming,layout.getResolvedLayoutDirection(),minEms,maxEms,minTextWidth,maxTextWidth,c.getAndroidContext().getResources().getDisplayMetrics().density,breakStrategy,hyphenationFrequency,justificationMode,textDirection,lineHeight));
  }
  final float textHeight=LayoutMeasureUtil.getHeight(textLayout.get());
switch (verticalGravity) {
case CENTER:
    textLayoutTranslationY.set((layoutHeight - textHeight) / 2);
  break;
case BOTTOM:
textLayoutTranslationY.set(layoutHeight - textHeight);
break;
default :
textLayoutTranslationY.set(0f);
break;
}
if (customEllipsisText != null && !customEllipsisText.equals("")) {
final int ellipsizedLineNumber=getEllipsizedLineNumber(textLayout.get());
if (ellipsizedLineNumber != -1) {
final CharSequence truncated=truncateText(text,customEllipsisText,textLayout.get(),ellipsizedLineNumber,layoutWidth);
Layout newLayout=createTextLayout(SizeSpec.makeSizeSpec((int)layoutWidth,EXACTLY),ellipsize,shouldIncludeFontPadding,maxLines,shadowRadius,shadowDx,shadowDy,shadowColor,isSingleLine,truncated,textColor,textColorStateList,linkColor,textSize,extraSpacing,spacingMultiplier,letterSpacing,textStyle,typeface,textAlignment,glyphWarming,layout.getResolvedLayoutDirection(),minEms,maxEms,minTextWidth,maxTextWidth,c.getAndroidContext().getResources().getDisplayMetrics().density,breakStrategy,hyphenationFrequency,justificationMode,textDirection,lineHeight);
processedText.set(truncated);
textLayout.set(newLayout);
}
}
final CharSequence resultText=processedText.get();
if (resultText instanceof Spanned) {
Spanned spanned=(Spanned)resultText;
clickableSpans.set(spanned.getSpans(0,resultText.length(),ClickableSpan.class));
imageSpans.set(spanned.getSpans(0,resultText.length(),ImageSpan.class));
}
}
