/** 
 * update article and add update log
 * @param model
 * @param modelName
 * @param remark
 */
public void update(InterfaceWithBLOBs model,String modelName,String remark) throws MyException {
  InterfaceWithBLOBs dbModel=interfaceDao.selectByPrimaryKey(model.getId());
  if (MyString.isEmpty(remark)) {
    remark=model.getInterfaceName();
  }
  Log log=Adapter.getLog(dbModel.getId(),modelName,remark,LogType.UPDATE,dbModel.getClass(),dbModel);
  logService.insert(log);
  super.update(model);
}
