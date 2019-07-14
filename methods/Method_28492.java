private void init(){
  if (isInEditMode())   return;
  if (isInEditMode())   return;
  setInputType(getInputType() | EditorInfo.IME_FLAG_NO_EXTRACT_UI | EditorInfo.IME_FLAG_NO_FULLSCREEN);
  setImeOptions(getImeOptions() | EditorInfo.IME_FLAG_NO_FULLSCREEN);
  TypeFaceHelper.applyTypeface(this);
}
