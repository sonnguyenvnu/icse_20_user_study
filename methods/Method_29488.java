/** 
 * ???????
 */
private TimeBetween fillWithValueDistriTime(HttpServletRequest request,Model model) throws ParseException {
  final String valueDistriDateFormat="yyyy-MM-dd";
  String valueDistriStartDateParam=request.getParameter("valueDistriStartDate");
  String valueDistriEndDateParam=request.getParameter("valueDistriEndDate");
  Date startDate;
  Date endDate;
  if (StringUtils.isBlank(valueDistriStartDateParam) || StringUtils.isBlank(valueDistriEndDateParam)) {
    SimpleDateFormat sdf=new SimpleDateFormat(valueDistriDateFormat);
    startDate=sdf.parse(sdf.format(new Date()));
    endDate=DateUtils.addDays(startDate,1);
    valueDistriStartDateParam=DateUtil.formatDate(startDate,valueDistriDateFormat);
    valueDistriEndDateParam=DateUtil.formatDate(endDate,valueDistriDateFormat);
  }
 else {
    endDate=DateUtil.parse(valueDistriEndDateParam,valueDistriDateFormat);
    startDate=DateUtil.parse(valueDistriStartDateParam,valueDistriDateFormat);
    if (endDate.getTime() - startDate.getTime() > TimeUnit.DAYS.toMillis(1)) {
      startDate=DateUtils.addDays(endDate,-1);
    }
  }
  model.addAttribute("valueDistriStartDate",valueDistriStartDateParam);
  model.addAttribute("valueDistriEndDate",valueDistriEndDateParam);
  long startTime=NumberUtils.toLong(DateUtil.formatDate(startDate,COLLECT_TIME_FORMAT));
  long endTime=NumberUtils.toLong(DateUtil.formatDate(endDate,COLLECT_TIME_FORMAT));
  return new TimeBetween(startTime,endTime,startDate,endDate);
}
