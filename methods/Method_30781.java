@Override public void setError(CharSequence error){
  super.setError(error);
  if (mEditTextBackground != null) {
    mEditTextBackground.setError(!TextUtils.isEmpty(error));
  }
}
