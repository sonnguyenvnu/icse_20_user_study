/** 
 * ??????? ????
 * @return
 */
@RequestMapping(params="customerlist") public ModelAndView customerlist(HttpServletRequest request){
  return new ModelAndView("com/jeecg/demo/orderOne2Many/jformOrderCustomerListBase");
}
