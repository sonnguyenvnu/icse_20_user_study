public String getDistributeDesc(){
  ValueSizeDistriEnum valueSizeDistriEnum=ValueSizeDistriEnum.getByType(distributeType);
  return valueSizeDistriEnum == null ? "" : valueSizeDistriEnum.getInfo();
}
