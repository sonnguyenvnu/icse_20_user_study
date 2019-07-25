/** 
 * ????????????????????????
 * @param request
 * @return
 * @throws Exception
 */
@RequestMapping(params="repair") @ResponseBody public AjaxJson repair(HttpServletRequest request) throws Exception {
  AjaxJson json=new AjaxJson();
  List<TSIcon> icons=systemService.loadAll(TSIcon.class);
  String rootpath=request.getSession().getServletContext().getRealPath("/");
  String csspath=request.getSession().getServletContext().getRealPath("/plug-in/accordion/css/icons.css");
  clearFile(csspath);
  for (  TSIcon c : icons) {
    File file=new File(rootpath + c.getIconPath());
    if (!file.exists()) {
      byte[] content=c.getIconContent();
      if (content != null) {
        BufferedImage imag=ImageIO.read(new ByteArrayInputStream(content));
        ImageIO.write(imag,"PNG",file);
      }
    }
    String css="." + c.getIconClas() + "{background:url('../images/" + c.getIconClas() + "." + c.getExtend() + "') no-repeat}";
    write(request,css);
  }
  json.setMsg(MutiLangUtil.paramAddSuccess("common.icon.style"));
  json.setSuccess(true);
  return json;
}
