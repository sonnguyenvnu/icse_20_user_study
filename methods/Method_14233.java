protected void internalImport(HttpServletRequest request,Properties options,long projectID) throws Exception {
  String url=null;
  ServletFileUpload upload=new ServletFileUpload();
  FileItemIterator iter=upload.getItemIterator(request);
  while (iter.hasNext()) {
    FileItemStream item=iter.next();
    String name=item.getFieldName().toLowerCase();
    InputStream stream=item.openStream();
    if (item.isFormField()) {
      if (name.equals("url")) {
        url=Streams.asString(stream);
      }
 else {
        options.put(name,Streams.asString(stream));
      }
    }
 else {
      String fileName=item.getName().toLowerCase();
      try {
        ProjectManager.singleton.importProject(projectID,stream,!fileName.endsWith(".tar"));
      }
  finally {
        stream.close();
      }
    }
  }
  if (url != null && url.length() > 0) {
    internalImportURL(request,options,projectID,url);
  }
}
