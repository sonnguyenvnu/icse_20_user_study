/** 
 * Create a temporary folder that includes the sketch's name in its title.
 */
public File makeTempFolder(){
  try {
    return Util.createTempFolder(name,"temp",null);
  }
 catch (  IOException e) {
    Messages.showWarning(Language.text("temp_dir.messages.bad_build_folder"),Language.text("temp_dir.messages.bad_build_folder.description"),e);
  }
  return null;
}
