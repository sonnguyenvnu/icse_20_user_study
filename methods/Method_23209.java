/** 
 * Create an offscreen graphics surface for drawing, in this case for a renderer that writes to a file (such as PDF or DXF).
 * @param path the name of the file (can be an absolute or relative path)
 */
public PGraphics createGraphics(int w,int h,String renderer,String path){
  return makeGraphics(w,h,renderer,path,false);
}
