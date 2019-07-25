/** 
 * ???????? Dao ??
 * @return ??
 */
public EntityOperator exec(){
  if (null != entity) {
    for (    Pojo pojo : pojoList) {
      if (null == pojo.getOperatingObject())       pojo.setOperatingObject(myObj);
      if (pojo.params().isEmpty())       pojo.addParamsBy(pojo.getOperatingObject());
    }
    updateCount=dao._exec(pojoList.toArray(new DaoStatement[pojoList.size()]));
  }
  return this;
}
