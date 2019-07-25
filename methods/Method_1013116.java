/** 
 * Allows you to edit the location of the scan
 */
void edit(){
  IStructuredSelection selection=(IStructuredSelection)fTableViewer.getSelection();
  String location=selection.getFirstElement().toString();
  File file=new File(location);
  String newloc=null;
  if (file.isDirectory()) {
    newloc=getDirectory(location);
  }
 else {
    newloc=getZipArchiveFile(location);
  }
  if (newloc != null) {
    fLocationList.remove(location);
    addLocation(newloc);
  }
}
