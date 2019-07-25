/** 
 * ????Properties??,???null!!!! ?????????url???!!!
 * @param props ????
 */
public void init(Properties props){
  if (dao != null) {
    throw new IllegalArgumentException("DaoUp is inited!!");
  }
  if (props.size() == 0) {
    throw new IllegalArgumentException("DaoUp props size=0!!!");
  }
  DataSource ds=buildDataSource(props);
  setDataSource(ds);
}
