private static void initEditText(EditText editText,CharSequence text,CharSequence initialText,CharSequence hint,TextUtils.TruncateAt ellipsize,@Nullable List<InputFilter> inputFilters,int minLines,int maxLines,int maxLength,float shadowRadius,float shadowDx,float shadowDy,int shadowColor,boolean isSingleLine,int textColor,ColorStateList textColorStateList,int hintColor,ColorStateList hintColorStateList,int linkColor,int highlightColor,ColorStateList tintColorStateList,int textSize,float extraSpacing,float spacingMultiplier,int textStyle,Typeface typeface,Layout.Alignment textAlignment,int gravity,boolean editable,int selection,int inputType,int rawInputType,int imeOptions,TextView.OnEditorActionListener editorActionListener,boolean isSingleLineWrap,boolean requestFocus,int cursorDrawableRes){
  if (isSingleLine) {
    inputType&=~EditorInfo.TYPE_TEXT_FLAG_MULTI_LINE;
  }
 else {
    inputType|=EditorInfo.TYPE_TEXT_FLAG_MULTI_LINE;
  }
  if (rawInputType != EditorInfo.TYPE_NULL) {
    editText.setSingleLine(isSingleLine);
    editText.setRawInputType(rawInputType);
  }
 else   if (inputType != editText.getInputType()) {
    editText.setSingleLine(isSingleLine);
    editText.setInputType(inputType);
  }
  if (isSingleLine && isSingleLineWrap) {
    editText.setHorizontallyScrolling(false);
  }
  InputFilter.LengthFilter lengthFilter=new InputFilter.LengthFilter(maxLength);
  if (inputFilters == null) {
    editText.setFilters(new InputFilter[]{lengthFilter});
  }
 else {
    inputFilters=new ArrayList<>(inputFilters);
    inputFilters.add(lengthFilter);
    editText.setFilters(inputFilters.toArray(new InputFilter[inputFilters.size()]));
  }
  if (!(text instanceof String) || !text.equals(editText.getText().toString())) {
    editText.setText(text);
  }
 else   if (initialText != null) {
    editText.setText(initialText);
  }
  CharSequence oldHint=editText.getHint();
  boolean hintsAreEqual=(oldHint == hint) || (oldHint != null && oldHint.equals(hint));
  if (!hintsAreEqual) {
    editText.setHint(hint);
  }
  editText.setEllipsize(ellipsize);
  editText.setMinLines(minLines);
  editText.setMaxLines(maxLines);
  editText.setShadowLayer(shadowRadius,shadowDx,shadowDy,shadowColor);
  editText.setLinkTextColor(linkColor);
  editText.setHighlightColor(highlightColor);
  editText.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
  editText.setLineSpacing(extraSpacing,spacingMultiplier);
  editText.setTypeface(typeface,textStyle);
  editText.setGravity(gravity);
  editText.setImeOptions(imeOptions);
  editText.setOnEditorActionListener(editorActionListener);
  editText.setFocusable(editable);
  editText.setFocusableInTouchMode(editable);
  editText.setClickable(editable);
  editText.setLongClickable(editable);
  editText.setCursorVisible(editable);
  @Nullable Editable editableText=editText.getText();
  int textLength=editableText != null ? editableText.length() : -1;
  if (selection > -1 && selection <= textLength) {
    editText.setSelection(selection);
  }
  if (textColor != 0 || textColorStateList == null) {
    editText.setTextColor(textColor);
  }
 else {
    editText.setTextColor(textColorStateList);
  }
  if (hintColor != 0 || hintColorStateList == null) {
    editText.setHintTextColor(hintColor);
  }
 else {
    editText.setHintTextColor(hintColorStateList);
  }
  if (tintColorStateList != null) {
    ViewCompat.setBackgroundTintList(editText,tintColorStateList);
  }
  if (requestFocus) {
    editText.requestFocus();
  }
  if (cursorDrawableRes != -1) {
    try {
      Field f=TextView.class.getDeclaredField("mCursorDrawableRes");
      f.setAccessible(true);
      f.set(editText,cursorDrawableRes);
    }
 catch (    Exception exception) {
    }
  }
switch (textAlignment) {
case ALIGN_NORMAL:
    if (SDK_INT >= JELLY_BEAN_MR1) {
      editText.setTextAlignment(TEXT_ALIGNMENT_TEXT_START);
    }
 else {
      editText.setGravity(gravity | Gravity.LEFT);
    }
  break;
case ALIGN_OPPOSITE:
if (SDK_INT >= JELLY_BEAN_MR1) {
  editText.setTextAlignment(TEXT_ALIGNMENT_TEXT_END);
}
 else {
  editText.setGravity(gravity | Gravity.RIGHT);
}
break;
case ALIGN_CENTER:
if (SDK_INT >= JELLY_BEAN_MR1) {
editText.setTextAlignment(TEXT_ALIGNMENT_CENTER);
}
 else {
editText.setGravity(gravity | Gravity.CENTER_HORIZONTAL);
}
break;
}
}
