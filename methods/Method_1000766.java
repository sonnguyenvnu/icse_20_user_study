public void process(ActionContext ac) throws Throwable {
  List<String> phArgs=ac.getPathArgs();
  HttpServletRequest req=ac.getRequest();
  if (ac.getReferObject() != null)   req.setAttribute(ActionContext.REFER_OBJECT,ac.getReferObject());
  Object[] args=adaptor.adapt(ac.getServletContext(),req,ac.getResponse(),phArgs.toArray(new String[phArgs.size()]));
  Object referObject=req.getAttribute(ActionContext.REFER_OBJECT);
  ac.setReferObject(referObject);
  req.removeAttribute(ActionContext.REFER_OBJECT);
  ac.setMethodArgs(args);
  doNext(ac);
}
