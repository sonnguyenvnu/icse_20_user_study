/** 
 * Add files to a folder to create an empty sketch. This can be overridden to add template files to a sketch for Modes that need them.
 * @param sketchFolder the directory where the new sketch should live
 * @param sketchName the name of the new sketch
 * @return the main file for the sketch to be opened via handleOpen()
 * @throws IOException if the file somehow already exists
 */
public File addTemplateFiles(File sketchFolder,String sketchName) throws IOException {
  File newbieFile=new File(sketchFolder,sketchName + "." + getDefaultExtension());
  try {
    File templateFolder=checkSketchbookTemplate();
    if (templateFolder == null) {
      templateFolder=getTemplateFolder();
    }
    if (templateFolder.exists()) {
      Util.copyDir(templateFolder,sketchFolder);
      File templateFile=new File(sketchFolder,"sketch." + getDefaultExtension());
      if (!templateFile.renameTo(newbieFile)) {
        System.err.println("Error while assigning the sketch template.");
      }
    }
 else {
      if (!newbieFile.createNewFile()) {
        System.err.println(newbieFile + " already exists.");
      }
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return newbieFile;
}
