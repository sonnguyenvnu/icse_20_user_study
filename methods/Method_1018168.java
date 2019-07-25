private void init() throws ImportException {
  this.accessController.checkPermission(AccessController.SERVICES,FeedServicesAccessControl.IMPORT_TEMPLATES);
  overallStatusMessage=uploadProgressService.addUploadStatus(this.importOptions.getUploadKey(),"Validating template for import");
  ImportTemplateOptions options=new ImportTemplateOptions();
  options.setUploadKey(this.importOptions.getUploadKey());
  options.setImportComponentOptions(this.importOptions.getImportComponentOptions());
  if (!isValidFileImport(fileName)) {
    this.overallStatusMessage.complete(false);
    throw new ImportException("Unable to import " + fileName + ".  The file must be a zip file or a Nifi Template xml file");
  }
  this.importTemplateOptions=options;
  setImportType();
  if (importType == ImportTemplate.TYPE.ARCHIVE) {
    initializeImportTemplateArchive();
  }
 else {
    initializeImportTemplateFromXml();
  }
}
