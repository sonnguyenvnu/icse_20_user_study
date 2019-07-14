/** 
 * ????????
 * @param statMap
 */
public static CostTimeDetailStatModel generateCostTimeDetailStatKey(AtomicLongMap<Integer> statMap){
  CostTimeDetailStatModel model=new CostTimeDetailStatModel();
  model.setMean(getMeanValue(statMap));
  model.setMedian(fillCostTimeDetailStatModel(model,statMap,50));
  model.setNinetyPercentMax(fillCostTimeDetailStatModel(model,statMap,90));
  model.setNinetyNinePercentMax(fillCostTimeDetailStatModel(model,statMap,99));
  model.setHundredMax(fillCostTimeDetailStatModel(model,statMap,100));
  return model;
}
