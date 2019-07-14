void setPrevCssSelector(final CssSelector prevCssSelector){
  this.prevCssSelector=prevCssSelector;
  prevCssSelector.nextCssSelector=this;
}
