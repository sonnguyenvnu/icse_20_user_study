public void edit(ProtectedTermsList file){
  Optional<ExternalFileType> termsFileType=OptionalUtil.orElse(ExternalFileTypes.getInstance().getExternalFileTypeByExt("terms"),ExternalFileTypes.getInstance().getExternalFileTypeByExt("txt"));
  String fileName=file.getLocation();
  try {
    JabRefDesktop.openExternalFileAnyFormat(new BibDatabaseContext(),fileName,termsFileType);
  }
 catch (  IOException e) {
    LOGGER.warn("Problem open protected terms file editor",e);
  }
}
