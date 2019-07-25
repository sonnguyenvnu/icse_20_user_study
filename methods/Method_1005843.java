/** 
 * ????
 * @param model
 * @param needAddLog ????????
 * @return
 */
public boolean update(Module model,boolean needAddLog) throws MyException {
  if (model == null) {
    return false;
  }
  if (needAddLog) {
    Module dbModule=getById(model.getId());
    Log log=Adapter.getLog(dbModule.getId(),L_MODULE_CHINESE,dbModule.getName(),LogType.UPDATE,dbModule.getClass(),dbModule);
    logService.insert(log);
  }
  model.setProjectId(null);
  if (model.getUrl() == null) {
    model.setUrl("");
  }
  return super.update(model);
}
