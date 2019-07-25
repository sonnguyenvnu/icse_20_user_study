public void update(Source model,boolean needAddLog) throws MyException {
  if (needAddLog) {
    Source dbModel=sourceDao.selectByPrimaryKey(model.getId());
    Log log=Adapter.getLog(dbModel.getId(),L_SOURCE_CHINESE,dbModel.getName(),LogType.UPDATE,dbModel.getClass(),dbModel);
    logService.insert(log);
  }
  sourceDao.updateByPrimaryKeySelective(model);
}
