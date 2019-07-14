/** 
 * Outputs bundle file to the response.
 */
protected void sendBundleFile(final HttpServletResponse resp,final File bundleFile) throws IOException {
  OutputStream out=resp.getOutputStream();
  FileInputStream fileInputStream=new FileInputStream(bundleFile);
  try {
    StreamUtil.copy(fileInputStream,out);
  }
  finally {
    StreamUtil.close(fileInputStream);
  }
}
