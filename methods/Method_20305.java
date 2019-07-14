public void setValue(@PluralsRes int pluralRes,int quantity,@Nullable Object[] formatArgs){
  if (pluralRes != 0) {
    this.pluralRes=pluralRes;
    this.quantity=quantity;
    this.formatArgs=formatArgs;
    string=null;
    stringRes=0;
  }
 else {
    handleInvalidStringRes();
  }
}
