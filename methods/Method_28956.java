/** 
 * ????????
 * @param costModel
 */
public static void collectCostAndValueDistribute(UsefulDataModel costModel){
  Long start=System.currentTimeMillis();
  try {
    String currentMinute=ClientReportConstant.getCollectTimeSDf().format(new Date());
    int cost=(int)costModel.getCost();
    String command=costModel.getCommand();
    String hostPort=costModel.getHostPort();
    int valueBytesLength=costModel.getValueBytesLength();
    CostTimeDetailStatKey costTimeDetailStatKey=new CostTimeDetailStatKey(currentMinute,command,hostPort);
    if (DATA_COST_TIME_MAP_ALL.containsKey(costTimeDetailStatKey)) {
      AtomicLongMap<Integer> stat=DATA_COST_TIME_MAP_ALL.get(costTimeDetailStatKey);
      stat.getAndIncrement(cost);
    }
 else {
      AtomicLongMap<Integer> stat=AtomicLongMap.create();
      stat.getAndIncrement(cost);
      AtomicLongMap<Integer> currentStat=DATA_COST_TIME_MAP_ALL.putIfAbsent(costTimeDetailStatKey,stat);
      if (currentStat != null) {
        currentStat.getAndIncrement(cost);
      }
    }
    ValueSizeDistriEnum redisValueSizeEnum=ValueSizeDistriEnum.getRightSizeBetween(valueBytesLength);
    if (redisValueSizeEnum != null) {
      ValueLengthModel valueLengthModel=new ValueLengthModel(redisValueSizeEnum,costModel.getCommand(),costModel.getHostPort());
      if (DATA_VALUE_LENGTH_DISTRIBUTE_MAP_ALL.containsKey(currentMinute)) {
        DATA_VALUE_LENGTH_DISTRIBUTE_MAP_ALL.get(currentMinute).getAndIncrement(valueLengthModel);
      }
 else {
        AtomicLongMap<ValueLengthModel> dataValueLengthMap=AtomicLongMap.create();
        dataValueLengthMap.getAndIncrement(valueLengthModel);
        AtomicLongMap<ValueLengthModel> currentDataValueLengthMap=DATA_VALUE_LENGTH_DISTRIBUTE_MAP_ALL.putIfAbsent(currentMinute,dataValueLengthMap);
        if (currentDataValueLengthMap != null) {
          currentDataValueLengthMap.getAndIncrement(valueLengthModel);
        }
      }
    }
    Long collectCostTime=System.currentTimeMillis() - start;
    if (COLLECTION_COST_TIME_MAP_ALL.containsKey(currentMinute)) {
      AtomicLongMap<Long> stat=COLLECTION_COST_TIME_MAP_ALL.get(currentMinute);
      stat.getAndIncrement(collectCostTime);
    }
 else {
      AtomicLongMap<Long> stat=AtomicLongMap.create();
      stat.getAndIncrement(collectCostTime);
      AtomicLongMap<Long> currentStat=COLLECTION_COST_TIME_MAP_ALL.putIfAbsent(currentMinute,stat);
      if (currentStat != null) {
        currentStat.getAndIncrement(collectCostTime);
      }
    }
  }
 catch (  Exception e) {
    logger.error("collect data error: " + e.getMessage());
  }
}
