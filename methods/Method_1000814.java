/** 
 * ??????
 * @param pks ??
 * @return ?? T
 */
public T fetchx(Object... pks){
  return dao().fetchx(getEntityClass(),pks);
}
