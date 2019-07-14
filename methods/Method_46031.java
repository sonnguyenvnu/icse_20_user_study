/** 
 * ???Invocation?????MeasureModel???????MeasureModel?????MeasureModel? ???Invocation????MeasureModel?????Invocation????MeasureModel????null?
 * @param invocationStat InvocationStat
 * @return MeasureModel
 */
@Override public MeasureModel buildMeasureModel(InvocationStat invocationStat){
  InvocationStatDimension statDimension=invocationStat.getDimension();
  String key=statDimension.getDimensionKey();
  MeasureModel measureModel=appServiceMeasureModels.get(key);
  if (measureModel == null) {
    measureModel=new MeasureModel(statDimension.getAppName(),statDimension.getService());
    MeasureModel oldMeasureModel=appServiceMeasureModels.putIfAbsent(key,measureModel);
    if (oldMeasureModel == null) {
      measureModel.addInvocationStat(invocationStat);
      return measureModel;
    }
 else {
      oldMeasureModel.addInvocationStat(invocationStat);
      return null;
    }
  }
 else {
    measureModel.addInvocationStat(invocationStat);
    return null;
  }
}
