private void checkUnsavedChanges(){
  if (mOriginal != null && mInput.isShown() && !mOriginal.equals(mInput.getText().toString())) {
    new MaterialDialog.Builder(this).title(R.string.unsavedchanges).content(R.string.unsavedchangesdesc).positiveText(R.string.yes).negativeText(R.string.no).positiveColor(getAccent()).negativeColor(getAccent()).onPositive((dialog,which) -> {
      saveFile(mInput.getText().toString());
      finish();
    }
).onNegative((dialog,which) -> finish()).build().show();
  }
 else {
    finish();
  }
}
