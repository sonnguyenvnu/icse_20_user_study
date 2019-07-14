private static void resolveStyleAttrsForTypedArray(TypedArray a,Output<TruncateAt> ellipsize,Output<Float> extraSpacing,Output<Boolean> shouldIncludeFontPadding,Output<Float> spacingMultiplier,Output<Integer> minLines,Output<Integer> maxLines,Output<Integer> minEms,Output<Integer> maxEms,Output<Integer> minTextWidth,Output<Integer> maxTextWidth,Output<Boolean> isSingleLine,Output<CharSequence> text,Output<ColorStateList> textColorStateList,Output<Integer> linkColor,Output<Integer> highlightColor,Output<Integer> textSize,Output<Layout.Alignment> textAlignment,Output<Integer> breakStrategy,Output<Integer> hyphenationFrequency,Output<Integer> justificationMode,Output<Integer> textStyle,Output<Float> shadowRadius,Output<Float> shadowDx,Output<Float> shadowDy,Output<Integer> shadowColor,Output<VerticalGravity> verticalGravity,Output<Typeface> typeface){
  int viewTextAlignment=View.TEXT_ALIGNMENT_GRAVITY;
  int gravity=Gravity.NO_GRAVITY;
  String fontFamily=null;
  for (int i=0, size=a.getIndexCount(); i < size; i++) {
    final int attr=a.getIndex(i);
    if (attr == R.styleable.Text_android_text) {
      text.set(a.getString(attr));
    }
 else     if (attr == R.styleable.Text_android_textColor) {
      textColorStateList.set(a.getColorStateList(attr));
    }
 else     if (attr == R.styleable.Text_android_textSize) {
      textSize.set(a.getDimensionPixelSize(attr,0));
    }
 else     if (attr == R.styleable.Text_android_ellipsize) {
      final int index=a.getInteger(attr,0);
      if (index > 0) {
        ellipsize.set(TRUNCATE_AT[index - 1]);
      }
    }
 else     if (attr == R.styleable.Text_android_textAlignment) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        viewTextAlignment=a.getInt(attr,-1);
        textAlignment.set(getAlignment(viewTextAlignment,gravity));
      }
    }
 else     if (attr == R.styleable.Text_android_gravity) {
      gravity=a.getInt(attr,-1);
      textAlignment.set(getAlignment(viewTextAlignment,gravity));
      verticalGravity.set(getVerticalGravity(gravity));
    }
 else     if (attr == R.styleable.Text_android_includeFontPadding) {
      shouldIncludeFontPadding.set(a.getBoolean(attr,false));
    }
 else     if (attr == R.styleable.Text_android_minLines) {
      minLines.set(a.getInteger(attr,-1));
    }
 else     if (attr == R.styleable.Text_android_maxLines) {
      maxLines.set(a.getInteger(attr,-1));
    }
 else     if (attr == R.styleable.Text_android_singleLine) {
      isSingleLine.set(a.getBoolean(attr,false));
    }
 else     if (attr == R.styleable.Text_android_textColorLink) {
      linkColor.set(a.getColor(attr,0));
    }
 else     if (attr == R.styleable.Text_android_textColorHighlight) {
      highlightColor.set(a.getColor(attr,0));
    }
 else     if (attr == R.styleable.Text_android_textStyle) {
      textStyle.set(a.getInteger(attr,0));
    }
 else     if (attr == R.styleable.Text_android_lineSpacingExtra) {
      extraSpacing.set(Float.valueOf(a.getDimensionPixelOffset(attr,0)));
    }
 else     if (attr == R.styleable.Text_android_lineSpacingMultiplier) {
      spacingMultiplier.set(a.getFloat(attr,0));
    }
 else     if (attr == R.styleable.Text_android_shadowDx) {
      shadowDx.set(a.getFloat(attr,0));
    }
 else     if (attr == R.styleable.Text_android_shadowDy) {
      shadowDy.set(a.getFloat(attr,0));
    }
 else     if (attr == R.styleable.Text_android_shadowRadius) {
      shadowRadius.set(a.getFloat(attr,0));
    }
 else     if (attr == R.styleable.Text_android_shadowColor) {
      shadowColor.set(a.getColor(attr,0));
    }
 else     if (attr == R.styleable.Text_android_minEms) {
      minEms.set(a.getInteger(attr,DEFAULT_EMS));
    }
 else     if (attr == R.styleable.Text_android_maxEms) {
      maxEms.set(a.getInteger(attr,DEFAULT_EMS));
    }
 else     if (attr == R.styleable.Text_android_minWidth) {
      minTextWidth.set(a.getDimensionPixelSize(attr,DEFAULT_MIN_WIDTH));
    }
 else     if (attr == R.styleable.Text_android_maxWidth) {
      maxTextWidth.set(a.getDimensionPixelSize(attr,DEFAULT_MAX_WIDTH));
    }
 else     if (attr == R.styleable.Text_android_fontFamily) {
      fontFamily=a.getString(attr);
    }
 else     if (attr == R.styleable.Text_android_breakStrategy) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        breakStrategy.set(a.getInt(attr,DEFAULT_BREAK_STRATEGY));
      }
    }
 else     if (attr == R.styleable.Text_android_hyphenationFrequency) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        hyphenationFrequency.set(a.getInt(attr,DEFAULT_HYPHENATION_FREQUENCY));
      }
    }
 else     if (attr == R.styleable.Text_android_justificationMode) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        justificationMode.set(a.getInt(attr,DEFAULT_JUSTIFICATION_MODE));
      }
    }
  }
  if (fontFamily != null) {
    final Integer styleValue=textStyle.get();
    typeface.set(Typeface.create(fontFamily,styleValue == null ? -1 : styleValue));
  }
}
