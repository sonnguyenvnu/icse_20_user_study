public void setWarning(@StringRes int text){
  if (!isStyleWarning) {
    removeError();
    setErrorEnabled(true);
    setErrorTextAppearance(R.style.warning_inputTextLayout);
    isStyleWarning=true;
  }
  super.setError(getContext().getString(text));
}
