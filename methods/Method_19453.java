@OnMeasure static void onMeasure(ComponentContext context,ComponentLayout layout,int widthSpec,int heightSpec,Size size,@Prop(resType=ResType.STRING) @Nullable CharSequence text,@Prop(optional=true) TruncateAt ellipsize,@Prop(optional=true,resType=ResType.BOOL) boolean shouldIncludeFontPadding,@Prop(optional=true,resType=ResType.INT) int minLines,@Prop(optional=true,resType=ResType.INT) int maxLines,@Prop(optional=true,resType=ResType.INT) int minEms,@Prop(optional=true,resType=ResType.INT) int maxEms,@Prop(optional=true,resType=ResType.DIMEN_SIZE) int minTextWidth,@Prop(optional=true,resType=ResType.DIMEN_SIZE) int maxTextWidth,@Prop(optional=true,resType=ResType.DIMEN_OFFSET) float shadowRadius,@Prop(optional=true,resType=ResType.DIMEN_OFFSET) float shadowDx,@Prop(optional=true,resType=ResType.DIMEN_OFFSET) float shadowDy,@Prop(optional=true,resType=ResType.COLOR) int shadowColor,@Prop(optional=true,resType=ResType.BOOL) boolean isSingleLine,@Prop(optional=true,resType=ResType.COLOR) int textColor,@Prop(optional=true) ColorStateList textColorStateList,@Prop(optional=true,resType=ResType.COLOR) int linkColor,@Prop(optional=true,resType=ResType.DIMEN_TEXT) int textSize,@Prop(optional=true,resType=ResType.DIMEN_OFFSET) float extraSpacing,@Prop(optional=true,resType=ResType.FLOAT) float spacingMultiplier,@Prop(optional=true,resType=ResType.FLOAT) float letterSpacing,@Prop(optional=true) int textStyle,@Prop(optional=true) @Nullable Typeface typeface,@Prop(optional=true) Alignment textAlignment,@Prop(optional=true) int breakStrategy,@Prop(optional=true) int hyphenationFrequency,@Prop(optional=true) int justificationMode,@Prop(optional=true) boolean glyphWarming,@Prop(optional=true) TextDirectionHeuristicCompat textDirection,@Prop(optional=true) boolean minimallyWide,@Prop(optional=true,resType=ResType.DIMEN_SIZE) int minimallyWideThreshold,@Prop(optional=true,resType=ResType.DIMEN_TEXT) float lineHeight,Output<Layout> measureLayout,Output<Integer> measuredWidth,Output<Integer> measuredHeight){
  if (TextUtils.isEmpty(text)) {
    measureLayout.set(null);
    size.width=0;
    size.height=0;
    return;
  }
  Layout newLayout=createTextLayout(widthSpec,ellipsize,shouldIncludeFontPadding,maxLines,shadowRadius,shadowDx,shadowDy,shadowColor,isSingleLine,text,textColor,textColorStateList,linkColor,textSize,extraSpacing,spacingMultiplier,letterSpacing,textStyle,typeface,textAlignment,glyphWarming,layout.getResolvedLayoutDirection(),minEms,maxEms,minTextWidth,maxTextWidth,context.getAndroidContext().getResources().getDisplayMetrics().density,breakStrategy,hyphenationFrequency,justificationMode,textDirection,lineHeight);
  measureLayout.set(newLayout);
  size.width=resolveWidth(widthSpec,newLayout,minimallyWide,minimallyWideThreshold);
  int preferredHeight=LayoutMeasureUtil.getHeight(newLayout);
  final int lineCount=newLayout.getLineCount();
  if (lineCount < minLines) {
    final TextPaint paint=newLayout.getPaint();
    final int layoutLineHeight=Math.round(paint.getFontMetricsInt(null) * spacingMultiplier + extraSpacing);
    preferredHeight+=layoutLineHeight * (minLines - lineCount);
  }
  size.height=SizeSpec.resolveSize(heightSpec,preferredHeight);
  if (size.width < 0 || size.height < 0) {
    size.width=Math.max(size.width,0);
    size.height=Math.max(size.height,0);
    final ComponentsLogger logger=context.getLogger();
    if (logger != null) {
      logger.emitMessage(ComponentsLogger.LogLevel.ERROR,"Text layout measured to less than 0 pixels");
    }
  }
  measuredWidth.set(size.width);
  measuredHeight.set(size.height);
}
