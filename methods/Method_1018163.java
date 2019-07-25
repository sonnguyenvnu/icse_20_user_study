public ImportFeed validate(){
  UploadProgressMessage feedImportStatusMessage=uploadProgressService.addUploadStatus(importFeedOptions.getUploadKey(),"Validating Feed import.");
  try {
    init();
    FeedMetadata metadata=importFeed.getFeedToImport();
    validateFeedCategory();
    String feedCategory=StringUtils.isNotBlank(importFeedOptions.getCategorySystemName()) ? importFeedOptions.getCategorySystemName() : metadata.getSystemCategoryName();
    FeedMetadata existingFeed=metadataAccess.read(() -> {
      return metadataService.getFeedByName(feedCategory,metadata.getSystemFeedName());
    }
,MetadataAccess.SERVICE);
    if (!validateOverwriteExistingFeed(existingFeed)) {
      return importFeed;
    }
    if (accessController.isEntityAccessControlled()) {
      if (!validateEntityAccess(existingFeed,feedCategory)) {
        return importFeed;
      }
    }
    if (!validateProperties()) {
      return importFeed;
    }
    if (!validateUserDatasources()) {
      return importFeed;
    }
    if (!validateUserDataSets()) {
      return importFeed;
    }
    TemplateImporter templateImporter=templateImporterFactory.apply(importFeed.getFileName(),file,importFeedOptions);
    ImportTemplate importTemplate=templateImporter.validate();
    importFeed.setTemplate(importTemplate);
    if (!importTemplate.isValid()) {
      importFeed.setValid(false);
      List<String> errorMessages=importTemplate.getTemplateResults().getAllErrors().stream().map(nifiError -> nifiError.getMessage()).collect(Collectors.toList());
      if (!errorMessages.isEmpty()) {
        for (        String msg : errorMessages) {
          importFeed.addErrorMessage(metadata,msg);
        }
      }
      return importFeed;
    }
  }
 catch (  Exception e) {
    feedImportStatusMessage.update("Validation error. Feed import error: " + e.getMessage(),false);
    throw new UnsupportedOperationException("Error importing template  " + fileName + ".  " + e.getMessage());
  }
  feedImportStatusMessage.update("Validated Feed import.",importFeed.isValid());
  return this.importFeed;
}
