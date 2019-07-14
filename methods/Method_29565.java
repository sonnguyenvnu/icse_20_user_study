/** 
 * ??hostPort????
 */
@RequestMapping(value="/checkInstanceHostPort") public ModelAndView checkInstanceHostPort(HttpServletRequest request,HttpServletResponse response,Model model){
  String hostPort=request.getParameter("instanceHostPort");
  if (StringUtils.isBlank(hostPort)) {
    model.addAttribute("status",SuccessEnum.FAIL.value());
    model.addAttribute("message","????");
    return new ModelAndView("");
  }
  String[] hostPortArr=hostPort.split(":");
  if (hostPortArr.length != 2) {
    model.addAttribute("status",SuccessEnum.FAIL.value());
    model.addAttribute("message","hostPort:" + hostPort + "????");
    return new ModelAndView("");
  }
  String host=hostPortArr[0];
  int port=NumberUtils.toInt(hostPortArr[1]);
  InstanceInfo instanceInfo=instanceDao.getAllInstByIpAndPort(host,port);
  if (instanceInfo == null) {
    model.addAttribute("status",SuccessEnum.FAIL.value());
    model.addAttribute("message","hostPort:" + hostPort + "???");
  }
 else {
    model.addAttribute("status",SuccessEnum.SUCCESS.value());
  }
  return new ModelAndView("");
}
