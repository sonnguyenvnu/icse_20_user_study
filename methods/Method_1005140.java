/** 
 * ??like(??)????
 * @param keyname
 * @param keyvalue1
 * @param keyvalue2
 */
public void like(String keyname,Object keyvalue){
  if (keyvalue != null && keyvalue != "") {
    criterionList.addPara(Restrictions.like(keyname,keyvalue));
    if (flag) {
      this.put(keyname,keyvalue);
    }
    flag=true;
  }
}
