public void edit(){
  LinkedFileEditDialogView dialog=new LinkedFileEditDialogView(this.linkedFile);
  Optional<LinkedFile> editedFile=dialog.showAndWait();
  editedFile.ifPresent(file -> {
    this.linkedFile.setLink(file.getLink());
    this.linkedFile.setDescription(file.getDescription());
    this.linkedFile.setFileType(file.getFileType());
  }
);
}
