protected void internalRespond(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  try {
    Project project=null;
    String importingJobID=request.getParameter("importingJobID");
    if (importingJobID != null) {
      long jobID=Long.parseLong(importingJobID);
      ImportingJob job=ImportingManager.getJob(jobID);
      if (job != null) {
        project=job.project;
      }
    }
    if (project == null) {
      project=getProject(request);
    }
    Engine engine=getEngine(request,project);
    String callback=request.getParameter("callback");
    int start=Math.min(project.rows.size(),Math.max(0,getIntegerParameter(request,"start",0)));
    int limit=Math.min(project.rows.size() - start,Math.max(0,getIntegerParameter(request,"limit",20)));
    Pool pool=new Pool();
    response.setCharacterEncoding("UTF-8");
    response.setHeader("Content-Type",callback == null ? "application/json" : "text/javascript");
    PrintWriter writer=response.getWriter();
    if (callback != null) {
      writer.write(callback);
      writer.write("(");
    }
    RowWritingVisitor rwv=new RowWritingVisitor(start,limit);
    SortingConfig sortingConfig=null;
    try {
      String sortingJson=request.getParameter("sorting");
      if (sortingJson != null) {
        sortingConfig=SortingConfig.reconstruct(sortingJson);
      }
    }
 catch (    IOException e) {
    }
    if (engine.getMode() == Mode.RowBased) {
      FilteredRows filteredRows=engine.getAllFilteredRows();
      RowVisitor visitor=rwv;
      if (sortingConfig != null) {
        SortingRowVisitor srv=new SortingRowVisitor(visitor);
        srv.initializeFromConfig(project,sortingConfig);
        if (srv.hasCriteria()) {
          visitor=srv;
        }
      }
      filteredRows.accept(project,visitor);
    }
 else {
      FilteredRecords filteredRecords=engine.getFilteredRecords();
      RecordVisitor visitor=rwv;
      if (sortingConfig != null) {
        SortingRecordVisitor srv=new SortingRecordVisitor(visitor);
        srv.initializeFromConfig(project,sortingConfig);
        if (srv.hasCriteria()) {
          visitor=srv;
        }
      }
      filteredRecords.accept(project,visitor);
    }
    for (    WrappedRow wr : rwv.results) {
      for (      Cell c : wr.row.cells) {
        if (c != null && c.recon != null) {
          pool.pool(c.recon);
        }
      }
    }
    JsonResult result=new JsonResult(engine.getMode(),rwv.results,rwv.total,engine.getMode() == Mode.RowBased ? project.rows.size() : project.recordModel.getRecordCount(),start,limit,pool);
    ParsingUtilities.defaultWriter.writeValue(writer,result);
    if (callback != null) {
      writer.write(")");
    }
    if (project.getMetadata() != null) {
      project.getMetadata().setRowCount(project.rows.size());
    }
  }
 catch (  Exception e) {
    respondException(response,e);
  }
}
