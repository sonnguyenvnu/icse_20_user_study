private static void setParams(EditText editText,@Nullable CharSequence hint,@Nullable Drawable background,float shadowRadius,float shadowDx,float shadowDy,int shadowColor,ColorStateList textColorStateList,ColorStateList hintColorStateList,int highlightColor,int textSize,Typeface typeface,int textAlignment,int gravity,boolean editable,int inputType,int imeOptions,@Nullable List<InputFilter> inputFilters,boolean multiline,@Nullable TextUtils.TruncateAt ellipsize,int minLines,int maxLines,int cursorDrawableRes,MovementMethod movementMethod,@Nullable CharSequence text){
  if (multiline) {
    inputType|=EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_FLAG_MULTI_LINE;
    editText.setMinLines(minLines);
    editText.setMaxLines(maxLines);
  }
 else {
    inputType&=~EditorInfo.TYPE_TEXT_FLAG_MULTI_LINE;
    editText.setLines(1);
  }
  setInputTypeIfChanged(editText,inputType);
  if (inputFilters != null) {
    editText.setFilters(inputFilters.toArray(new InputFilter[inputFilters.size()]));
  }
 else {
    editText.setFilters(NO_FILTERS);
  }
  editText.setHint(hint);
  if (SDK_INT < JELLY_BEAN) {
    editText.setBackgroundDrawable(background);
  }
 else {
    editText.setBackground(background);
  }
  if (background == null || !background.getPadding(sBackgroundPaddingRect)) {
    editText.setPadding(0,0,0,0);
  }
  editText.setShadowLayer(shadowRadius,shadowDx,shadowDy,shadowColor);
  editText.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
  editText.setTypeface(typeface,0);
  editText.setGravity(gravity);
  editText.setImeOptions(imeOptions);
  editText.setFocusable(editable);
  editText.setFocusableInTouchMode(editable);
  editText.setClickable(editable);
  editText.setLongClickable(editable);
  editText.setCursorVisible(editable);
  editText.setTextColor(textColorStateList);
  editText.setHintTextColor(hintColorStateList);
  editText.setHighlightColor(highlightColor);
  editText.setMovementMethod(movementMethod);
  if (cursorDrawableRes != -1) {
    try {
      Field f=TextView.class.getDeclaredField("mCursorDrawableRes");
      f.setAccessible(true);
      f.set(editText,cursorDrawableRes);
    }
 catch (    Exception exception) {
    }
  }
  editText.setEllipsize(ellipsize);
  if (SDK_INT >= JELLY_BEAN_MR1) {
    editText.setTextAlignment(textAlignment);
  }
  if (text != null && !equals(editText.getText().toString(),text.toString())) {
    editText.setText(text);
  }
}
