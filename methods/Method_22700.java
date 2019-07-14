/** 
 * Build the list of files. <P> Generally this is only done once, rather than each time a change is made, because otherwise it gets to be a nightmare to keep track of what files went where, because not all the data will be saved to disk. <P> This also gets called when the main sketch file is renamed, because the sketch has to be reloaded from a different folder. <P> Another exception is when an external editor is in use, in which case the load happens each time "run" is hit.
 */
protected void load(){
  codeFolder=new File(folder,"code");
  dataFolder=new File(folder,"data");
  List<String> filenames=new ArrayList<>();
  List<String> extensions=new ArrayList<>();
  getSketchCodeFiles(filenames,extensions);
  codeCount=filenames.size();
  code=new SketchCode[codeCount];
  for (int i=0; i < codeCount; i++) {
    String filename=filenames.get(i);
    String extension=extensions.get(i);
    code[i]=new SketchCode(new File(folder,filename),extension);
  }
  for (int i=1; i < codeCount; i++) {
    if (code[i].getFile().equals(primaryFile)) {
      SketchCode temp=code[0];
      code[0]=code[i];
      code[i]=temp;
      break;
    }
  }
  sortCode();
  if (editor != null) {
    setCurrentCode(0);
  }
}
