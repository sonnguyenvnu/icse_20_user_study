/** 
 * ????
 * @param datacubeType ????
 * @param beginDate ????
 * @param offset ?? ?????? ?? offset=1 ??? beginDate?????????
 * @see {@link #datacube(DatacubeType,Date,Date)}
 * @throws WeixinException
 */
public List<?> datacube(DatacubeType datacubeType,Date beginDate,int offset) throws WeixinException {
  Calendar ca=Calendar.getInstance();
  ca.setTime(beginDate);
  ca.add(Calendar.DAY_OF_MONTH,offset);
  return datacube(datacubeType,beginDate,ca.getTime());
}
