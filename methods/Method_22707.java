/** 
 * This is called upon return from entering a new file name. (that is, from either newCode or renameCode after the prompt)
 */
protected void nameCode(String newName){
  newName=newName.trim();
  if (newName.length() == 0) {
    return;
  }
  ensureExistence();
  if (newName.indexOf('.') == -1) {
    newName+="." + (renamingCode ? mode.getDefaultExtension() : mode.getModuleExtension());
  }
  if (renamingCode) {
    if (newName.equalsIgnoreCase(current.getFileName())) {
      return;
    }
  }
  if (newName.startsWith(".")) {
    Messages.showWarning(Language.text("name.messages.problem_renaming"),Language.text("name.messages.starts_with_dot.description"));
    return;
  }
  int dot=newName.lastIndexOf('.');
  String newExtension=newName.substring(dot + 1).toLowerCase();
  if (!mode.validExtension(newExtension)) {
    Messages.showWarning(Language.text("name.messages.problem_renaming"),Language.interpolate("name.messages.invalid_extension.description",newExtension));
    return;
  }
  if (!mode.isDefaultExtension(newExtension)) {
    if (renamingCode) {
      if (current == code[0]) {
        Messages.showWarning(Language.text("name.messages.problem_renaming"),Language.interpolate("name.messages.main_java_extension.description",newExtension));
        return;
      }
    }
  }
  String shortName=newName.substring(0,dot);
  String sanitaryName=Sketch.sanitizeName(shortName);
  if (!shortName.equals(sanitaryName)) {
    newName=sanitaryName + "." + newExtension;
  }
  if (!(renamingCode && sanitaryName.equals(current.getPrettyName()))) {
    for (    SketchCode c : code) {
      if (c != current && sanitaryName.equalsIgnoreCase(c.getPrettyName())) {
        Messages.showMessage(Language.text("name.messages.new_sketch_exists"),Language.interpolate("name.messages.new_sketch_exists.description",c.getFileName(),folder.getAbsolutePath()));
        return;
      }
    }
  }
  File newFile=new File(folder,newName);
  if (renamingCode) {
    if (currentIndex == 0) {
      String folderName=newName.substring(0,newName.indexOf('.'));
      File newFolder=new File(folder.getParentFile(),folderName);
      if (newFolder.exists()) {
        Messages.showWarning(Language.text("name.messages.new_folder_exists"),Language.interpolate("name.messages.new_folder_exists.description",newName));
        return;
      }
      boolean success=folder.renameTo(newFolder);
      if (!success) {
        Messages.showWarning(Language.text("name.messages.error"),Language.text("name.messages.no_rename_folder.description"));
        return;
      }
      current.setFolder(newFolder);
      newFile=new File(newFolder,newName);
      if (!current.renameTo(newFile,newExtension)) {
        Messages.showWarning(Language.text("name.messages.error"),Language.interpolate("name.messages.no_rename_file.description",current.getFileName(),newFile.getName()));
        return;
      }
      for (int i=1; i < codeCount; i++) {
        code[i].setFolder(newFolder);
      }
      updateInternal(sanitaryName,newFolder);
    }
 else {
      if (!current.renameTo(newFile,newExtension)) {
        Messages.showWarning(Language.text("name.messages.error"),Language.interpolate("name.messages.no_rename_file.description",current.getFileName(),newFile.getName()));
        return;
      }
    }
  }
 else {
    try {
      if (!newFile.createNewFile()) {
        throw new IOException("createNewFile() returned false");
      }
    }
 catch (    IOException e) {
      Messages.showWarning(Language.text("name.messages.error"),Language.interpolate("name.messages.no_create_file.description",newFile,folder.getAbsolutePath()),e);
      return;
    }
    SketchCode newCode=new SketchCode(newFile,newExtension);
    insertCode(newCode);
  }
  sortCode();
  setCurrentCode(newName);
  editor.rebuildHeader();
}
