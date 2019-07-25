public void execute(SimulatePipelineRequest.Parsed request,ActionListener<SimulatePipelineResponse> listener){
  threadPool.executor(THREAD_POOL_NAME).execute(new ActionRunnable<SimulatePipelineResponse>(listener){
    @Override protected void doRun() throws Exception {
      List<SimulateDocumentResult> responses=new ArrayList<>();
      for (      IngestDocument ingestDocument : request.getDocuments()) {
        responses.add(executeDocument(request.getPipeline(),ingestDocument,request.isVerbose()));
      }
      listener.onResponse(new SimulatePipelineResponse(request.getPipeline().getId(),request.isVerbose(),responses));
    }
  }
);
}
