@Override public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  long jobID=Long.parseLong(request.getParameter("jobID"));
  ImportingJob job=ImportingManager.getJob(jobID);
  Writer w=response.getWriter();
  if (job == null) {
    ParsingUtilities.defaultWriter.writeValue(w,new JobStatusResponse("error","No such import job",null));
  }
 else {
    ParsingUtilities.defaultWriter.writeValue(w,new JobStatusResponse("ok",null,job));
  }
}
