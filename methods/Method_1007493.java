public boolean check(HttpResponse resp,File file) throws IOException, Pausable {
  byte[] status=HttpResponse.ST_OK;
  String msg="";
  if (!file.exists()) {
    status=HttpResponse.ST_NOT_FOUND;
    msg="File Not Found: " + file.getName();
  }
 else   if (!file.canRead()) {
    status=HttpResponse.ST_FORBIDDEN;
    msg="Unable to read file " + file.getName();
  }
 else {
    try {
      String path=file.getCanonicalPath();
      if (!path.startsWith(baseDirectoryName)) {
        throw new SecurityException();
      }
    }
 catch (    Exception e) {
      status=HttpResponse.ST_FORBIDDEN;
      msg="Error retrieving " + file.getName() + ":<br>" + e.getMessage();
    }
  }
  if (status != HttpResponse.ST_OK) {
    problem(file,resp,status,msg);
    return false;
  }
 else {
    return true;
  }
}
