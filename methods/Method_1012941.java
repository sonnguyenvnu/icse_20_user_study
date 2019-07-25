@Override public void send(HttpServletResponse resp) throws IOException {
  resp.setContentType("image/png");
  GoogleCloudStorageHelper.serve(resp,blobKey);
}
