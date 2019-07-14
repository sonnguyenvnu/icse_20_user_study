public String getAmountDesc(){
  if (this.getFundDirection().equals(AccountFundDirectionEnum.ADD.name())) {
    return "<span style=\"color: blue;\">+" + this.amount.doubleValue() + "</span>";
  }
 else {
    return "<span style=\"color: red;\">-" + this.amount.doubleValue() + "</span>";
  }
}
