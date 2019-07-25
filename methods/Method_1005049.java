@Override public Response details() throws OperationException {
  final Context context=userFactory.createContext();
  return Response.ok(graphFactory.getGraph().execute(new GetAllJobDetails(),context)).header(GAFFER_MEDIA_TYPE_HEADER,GAFFER_MEDIA_TYPE).header(JOB_ID_HEADER,context.getJobId()).build();
}
