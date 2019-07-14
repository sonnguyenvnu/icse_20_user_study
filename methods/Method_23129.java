/** 
 * See selectInput() for details.
 * @webref output:files
 * @param prompt message to the user
 * @param callback name of the method to be called when the selection is made
 */
static public void selectOutput(Frame parent,String prompt,File file,Callback callback){
  selectImpl(parent,prompt,file,callback,FileDialog.SAVE);
}
