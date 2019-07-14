@ModelAttribute public void ajaxAttribute(WebRequest request,Model model){
  model.addAttribute("ajaxRequest",AjaxUtils.isAjaxRequest(request));
}
