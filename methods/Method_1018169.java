public boolean validate(){
  validateNiFiTemplateImport();
  return this.importTemplate.isValid();
}
