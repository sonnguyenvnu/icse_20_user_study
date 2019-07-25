/** 
 * ????
 * @param datacubeType ????
 * @param offset ?? ?????? ?? offset=1 ??? beginDate?????????
 * @param endDate ????
 * @see {@link #datacube(DatacubeType,Date,Date)}
 * @throws WeixinException
 */
public List<?> datacube(DatacubeType datacubeType,int offset,Date endDate) throws WeixinException {
  Calendar ca=Calendar.getInstance();
  ca.setTime(endDate);
  ca.add(Calendar.DAY_OF_MONTH,0 - offset);
  return datacube(datacubeType,ca.getTime(),endDate);
}
