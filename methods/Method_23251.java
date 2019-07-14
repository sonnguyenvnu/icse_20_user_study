/** 
 * ( begin auto-generated from createOutput.xml ) Similar to <b>createInput()</b>, this creates a Java <b>OutputStream</b> for a given filename or path. The file will be created in the sketch folder, or in the same folder as an exported application. <br /><br /> If the path does not exist, intermediate folders will be created. If an exception occurs, it will be printed to the console, and <b>null</b> will be returned. <br /><br /> This function is a convenience over the Java approach that requires you to 1) create a FileOutputStream object, 2) determine the exact file location, and 3) handle exceptions. Exceptions are handled internally by the function, which is more appropriate for "sketch" projects. <br /><br /> If the output filename ends with <b>.gz</b>, the output will be automatically GZIP compressed as it is written. ( end auto-generated )
 * @webref output:files
 * @param filename name of the file to open
 * @see PApplet#createInput(String)
 * @see PApplet#selectOutput(String,String)
 */
public OutputStream createOutput(String filename){
  return createOutput(saveFile(filename));
}
