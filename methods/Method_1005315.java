@RequestMapping(params="delete") @ResponseBody public AjaxJson delete(TSUser user,@RequestParam String deleteType,HttpServletRequest req){
  if (deleteType.equals("delete")) {
    return this.del(user,req);
  }
 else   if (deleteType.equals("deleteTrue")) {
    return this.trueDel(user,req);
  }
 else {
    AjaxJson j=new AjaxJson();
    j.setMsg("????????,???.");
    return j;
  }
}
