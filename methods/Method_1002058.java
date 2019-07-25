@Override protected void init(UploadService service,Intent intent) throws IOException {
  super.init(service,intent);
  this.httpParams=intent.getParcelableExtra(HttpUploadTaskParameters.PARAM_HTTP_TASK_PARAMETERS);
}
