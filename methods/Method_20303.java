public void setValue(@StringRes int stringRes,@Nullable Object[] formatArgs){
  if (stringRes != 0) {
    this.stringRes=stringRes;
    this.formatArgs=formatArgs;
    string=null;
    pluralRes=0;
  }
 else {
    handleInvalidStringRes();
  }
}
