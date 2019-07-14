@OnLoadStyle static void onLoadStyle(ComponentContext c,Output<TextUtils.TruncateAt> ellipsize,Output<Float> spacingMultiplier,Output<Integer> minLines,Output<Integer> maxLines,Output<Boolean> isSingleLine,Output<CharSequence> text,Output<ColorStateList> textColorStateList,Output<Integer> linkColor,Output<Integer> highlightColor,Output<Integer> textSize,Output<Layout.Alignment> textAlignment,Output<Integer> textStyle,Output<Float> shadowRadius,Output<Float> shadowDx,Output<Float> shadowDy,Output<Integer> shadowColor,Output<Integer> gravity,Output<Integer> inputType,Output<Integer> imeOptions){
  final TypedArray a=c.obtainStyledAttributes(R.styleable.Text,0);
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
      if (SDK_INT >= JELLY_BEAN_MR1) {
        int viewTextAlignment=a.getInt(attr,-1);
        textAlignment.set(getAlignment(viewTextAlignment,Gravity.NO_GRAVITY));
      }
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
 else     if (attr == R.styleable.Text_android_gravity) {
      gravity.set(a.getInteger(attr,0));
    }
 else     if (attr == R.styleable.Text_android_inputType) {
      inputType.set(a.getInteger(attr,0));
    }
 else     if (attr == R.styleable.Text_android_imeOptions) {
      imeOptions.set(a.getInteger(attr,0));
    }
  }
  a.recycle();
}
