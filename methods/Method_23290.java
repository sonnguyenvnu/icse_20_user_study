/** 
 * ( begin auto-generated from beginRecord.xml ) Opens a new file and all subsequent drawing functions are echoed to this file as well as the display window. The <b>beginRecord()</b> function requires two parameters, the first is the renderer and the second is the file name. This function is always used with <b>endRecord()</b> to stop the recording process and close the file. <br /> <br /> Note that beginRecord() will only pick up any settings that happen after it has been called. For instance, if you call textFont() before beginRecord(), then that font will not be set for the file that you're recording to. ( end auto-generated )
 * @webref output:files
 * @param renderer PDF or SVG
 * @param filename filename for output
 * @see PApplet#endRecord()
 */
public PGraphics beginRecord(String renderer,String filename){
  filename=insertFrame(filename);
  PGraphics rec=createGraphics(width,height,renderer,filename);
  beginRecord(rec);
  return rec;
}
