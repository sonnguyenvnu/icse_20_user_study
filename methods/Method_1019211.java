public HoodieRollingStatMetadata merge(HoodieRollingStatMetadata rollingStatMetadata){
  for (  Map.Entry<String,Map<String,HoodieRollingStat>> stat : rollingStatMetadata.partitionToRollingStats.entrySet()) {
    for (    Map.Entry<String,HoodieRollingStat> innerStat : stat.getValue().entrySet()) {
      this.addRollingStat(stat.getKey(),innerStat.getValue());
    }
  }
  return this;
}
