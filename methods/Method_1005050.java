@Override public Response details(final String id) throws OperationException {
  final Context context=userFactory.createContext();
  return Response.ok(graphFactory.getGraph().execute(new GetJobDetails.Builder().jobId(id).build(),context)).header(GAFFER_MEDIA_TYPE_HEADER,GAFFER_MEDIA_TYPE).header(JOB_ID_HEADER,context.getJobId()).build();
}
