public String getPayWayNameDesc(){
  return PayWayEnum.getEnum(this.getPayWayCode()).getDesc() + "-" + PayTypeEnum.getEnum(this.getPayTypeCode()).getDesc();
}
