@NonNull public SpannableBuilder getDisplayTitle(boolean isFromProfile,boolean gistView){
  SpannableBuilder spannableBuilder=SpannableBuilder.builder();
  boolean addDescription=true;
  if (!isFromProfile) {
    if (owner != null) {
      spannableBuilder.bold(owner.getLogin());
    }
 else     if (user != null) {
      spannableBuilder.bold(user.getLogin());
    }
 else {
      spannableBuilder.bold("Anonymous");
    }
    if (!gistView) {
      List<FilesListModel> files=getFilesAsList();
      if (!files.isEmpty()) {
        FilesListModel filesListModel=files.get(0);
        if (!InputHelper.isEmpty(filesListModel.getFilename()) && filesListModel.getFilename().trim().length() > 2) {
          spannableBuilder.append(" ").append("/").append(" ").append(filesListModel.getFilename());
          addDescription=false;
        }
      }
    }
  }
  if (!InputHelper.isEmpty(description) && addDescription) {
    if (!InputHelper.isEmpty(spannableBuilder.toString())) {
      spannableBuilder.append(" ").append("/").append(" ");
    }
    spannableBuilder.append(description);
  }
  if (InputHelper.isEmpty(spannableBuilder.toString())) {
    if (isFromProfile) {
      List<FilesListModel> files=getFilesAsList();
      if (!files.isEmpty()) {
        FilesListModel filesListModel=files.get(0);
        if (!InputHelper.isEmpty(filesListModel.getFilename()) && filesListModel.getFilename().trim().length() > 2) {
          spannableBuilder.append(" ").append(filesListModel.getFilename());
        }
      }
    }
  }
  return spannableBuilder;
}
