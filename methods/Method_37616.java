protected void putFile(final String name,final FileUpload value){
  if (requestFiles == null) {
    requestFiles=new HashMap<>();
  }
  FileUpload[] fileUploads=requestFiles.get(name);
  if (fileUploads != null) {
    fileUploads=ArraysUtil.append(fileUploads,value);
  }
 else {
    fileUploads=new FileUpload[]{value};
  }
  requestFiles.put(name,fileUploads);
}
