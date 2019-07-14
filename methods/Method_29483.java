/** 
 * ????????
 */
private TimeBetween fillWithClientExceptionTime(HttpServletRequest request,Model model) throws ParseException {
  final String exceptionDateFormat="yyyy-MM-dd";
  String exceptionStartDateParam=request.getParameter("exceptionStartDate");
  String exceptionEndDateParam=request.getParameter("exceptionEndDate");
  Date startDate;
  Date endDate;
  if (StringUtils.isBlank(exceptionStartDateParam) || StringUtils.isBlank(exceptionEndDateParam)) {
    SimpleDateFormat sdf=new SimpleDateFormat(exceptionDateFormat);
    startDate=sdf.parse(sdf.format(new Date()));
    endDate=DateUtils.addDays(startDate,1);
    exceptionStartDateParam=DateUtil.formatDate(startDate,exceptionDateFormat);
    exceptionEndDateParam=DateUtil.formatDate(endDate,exceptionDateFormat);
  }
 else {
    endDate=DateUtil.parse(exceptionEndDateParam,exceptionDateFormat);
    startDate=DateUtil.parse(exceptionStartDateParam,exceptionDateFormat);
    if (endDate.getTime() - startDate.getTime() > TimeUnit.DAYS.toMillis(7)) {
      startDate=DateUtils.addDays(endDate,-7);
    }
  }
  model.addAttribute("exceptionStartDate",exceptionStartDateParam);
  model.addAttribute("exceptionEndDate",exceptionEndDateParam);
  long startTime=NumberUtils.toLong(DateUtil.formatDate(startDate,COLLECT_TIME_FORMAT));
  long endTime=NumberUtils.toLong(DateUtil.formatDate(endDate,COLLECT_TIME_FORMAT));
  return new TimeBetween(startTime,endTime,startDate,endDate);
}
