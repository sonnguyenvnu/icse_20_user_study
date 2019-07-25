@Override public void execute(){
  if (panel.getSelectedEntries().size() != 1) {
    dialogService.notify(Localization.lang("This operation requires exactly one item to be selected."));
    return;
  }
  BibEntry entry=panel.getSelectedEntries().get(0);
  Path workingDirectory=panel.getBibDatabaseContext().getFirstExistingFileDir(Globals.prefs.getFilePreferences()).orElse(Paths.get(Globals.prefs.get(JabRefPreferences.WORKING_DIRECTORY)));
  FileDialogConfiguration fileDialogConfiguration=new FileDialogConfiguration.Builder().withInitialDirectory(workingDirectory).build();
  dialogService.showFileOpenDialog(fileDialogConfiguration).ifPresent(newFile -> {
    LinkedFile linkedFile=LinkedFilesEditorViewModel.fromFile(newFile,panel.getBibDatabaseContext().getFileDirectoriesAsPaths(Globals.prefs.getFilePreferences()),ExternalFileTypes.getInstance());
    LinkedFileEditDialogView dialog=new LinkedFileEditDialogView(linkedFile);
    dialog.showAndWait().ifPresent(editedLinkedFile -> {
      Optional<FieldChange> fieldChange=entry.addFile(editedLinkedFile);
      fieldChange.ifPresent(change -> {
        UndoableFieldChange ce=new UndoableFieldChange(change);
        panel.getUndoManager().addEdit(ce);
        panel.markBaseChanged();
      }
);
    }
);
  }
);
}
