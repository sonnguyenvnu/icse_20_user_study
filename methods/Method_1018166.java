public UploadProgressMessage start(){
  return uploadProgressService.addUploadStatus(importTemplateOptions.getUploadKey(),"Importing the NiFi flow ");
}
