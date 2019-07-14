public String getFundDirectionDesc(){
  return AccountFundDirectionEnum.getEnum(this.getFundDirection()).getLabel();
}
