/** 
 * //    @ExceptionHandler(Exception.class)
 */
public Object handler(HttpServletRequest request,HttpServletResponse response,Exception e){
  e.printStackTrace();
  ModelAndView mav=new ModelAndView();
  mav.addObject("exception",e);
  mav.addObject("url",request.getRequestURL());
  mav.setViewName(ERROR_VIEW);
  return mav;
}
