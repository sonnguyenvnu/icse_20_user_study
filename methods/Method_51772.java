@InternalApi @Deprecated private void setModifier(boolean enable,int mask){
  if (enable) {
    this.modifiers|=mask;
  }
 else {
    this.modifiers&=~mask;
  }
}
