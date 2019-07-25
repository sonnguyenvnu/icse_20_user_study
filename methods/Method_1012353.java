public StepData cover(StepBean stepBean){
  StepData stepData=new StepData();
  stepData.setUid(stepBean.getUser().getObjectId());
  stepData.setTime(stepBean.getTime());
  stepData.setId(stepBean.getObjectId());
  stepData.setStepCount(stepBean.getStepCount());
  return stepData;
}
