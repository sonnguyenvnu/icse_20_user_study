static public void selectOutput(String prompt,String callbackMethod,File file,Object callbackObject,Frame parent,PApplet sketch){
  selectImpl(prompt,callbackMethod,file,callbackObject,parent,FileDialog.SAVE,sketch);
}
