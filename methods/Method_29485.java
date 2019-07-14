/** 
 * ????????
 * @throws ParseException 
 */
private TimeBetween fillWithCostDateFormat(HttpServletRequest request,Model model) throws ParseException {
  final String costDistriDateFormat="yyyy-MM-dd";
  String costDistriStartDateParam=request.getParameter("costDistriStartDate");
  String costDistriEndDateParam=request.getParameter("costDistriEndDate");
  Date startDate;
  Date endDate;
  if (StringUtils.isBlank(costDistriStartDateParam) || StringUtils.isBlank(costDistriEndDateParam)) {
    SimpleDateFormat sdf=new SimpleDateFormat(costDistriDateFormat);
    startDate=sdf.parse(sdf.format(new Date()));
    endDate=DateUtils.addDays(startDate,1);
    costDistriStartDateParam=DateUtil.formatDate(startDate,costDistriDateFormat);
    costDistriEndDateParam=DateUtil.formatDate(endDate,costDistriDateFormat);
  }
 else {
    endDate=DateUtil.parse(costDistriEndDateParam,costDistriDateFormat);
    startDate=DateUtil.parse(costDistriStartDateParam,costDistriDateFormat);
    if (endDate.getTime() - startDate.getTime() > TimeUnit.DAYS.toMillis(1)) {
      startDate=DateUtils.addDays(endDate,-1);
    }
  }
  model.addAttribute("costDistriStartDate",costDistriStartDateParam);
  model.addAttribute("costDistriEndDate",costDistriEndDateParam);
  long startTime=NumberUtils.toLong(DateUtil.formatDate(startDate,COLLECT_TIME_FORMAT));
  long endTime=NumberUtils.toLong(DateUtil.formatDate(endDate,COLLECT_TIME_FORMAT));
  return new TimeBetween(startTime,endTime,startDate,endDate);
}
