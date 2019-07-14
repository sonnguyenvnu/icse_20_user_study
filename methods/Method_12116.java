@RequestMapping(value="/getNewMessage",produces="text/html") @ResponseBody public String getNewMessage(@RequestParam(value="userid",required=true) String userId,Model model){
  return "";
}
