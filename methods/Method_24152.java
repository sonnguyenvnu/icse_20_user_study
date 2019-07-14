/** 
 * Calls ortho() with the proper parameters for Processing's standard orthographic projection.
 */
@Override public void ortho(){
  ortho(-width / 2f,width / 2f,-height / 2f,height / 2f,0,eyeDist * 10);
}
