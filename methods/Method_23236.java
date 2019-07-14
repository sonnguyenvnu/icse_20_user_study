static public void selectInput(String prompt,String callbackMethod,File file,Object callbackObject,Frame parent,PApplet sketch){
  selectImpl(prompt,callbackMethod,file,callbackObject,parent,FileDialog.LOAD,sketch);
}
