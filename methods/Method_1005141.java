/** 
 * ??between(??)????
 * @param keyname
 * @param keyvalue1
 * @param keyvalue2
 */
public void between(String keyname,Object keyvalue1,Object keyvalue2){
  Criterion c=null;
  if (oConvertUtils.isNotEmpty(keyvalue1) && oConvertUtils.isNotEmpty(keyvalue2)) {
    c=Restrictions.between(keyname,keyvalue1,keyvalue2);
  }
 else   if (oConvertUtils.isNotEmpty(keyvalue1)) {
    c=Restrictions.ge(keyname,keyvalue1);
  }
 else   if (oConvertUtils.isNotEmpty(keyvalue2)) {
    c=Restrictions.le(keyname,keyvalue2);
  }
  criterionList.add(c);
}
