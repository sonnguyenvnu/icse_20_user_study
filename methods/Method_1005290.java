/** 
 * ????????????
 * @return
 */
@RequestMapping(params="addorupdate") public ModelAndView addorupdate(TSTimeTaskEntity timeTask,HttpServletRequest req){
  if (StringUtil.isNotEmpty(timeTask.getId())) {
    timeTask=timeTaskService.getEntity(TSTimeTaskEntity.class,timeTask.getId());
    req.setAttribute("timeTaskPage",timeTask);
  }
  return new ModelAndView("system/timetask/timeTask");
}
