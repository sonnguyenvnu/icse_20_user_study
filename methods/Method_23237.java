static public void selectInput(String prompt,String callbackMethod,File file,Object callbackObject,Frame parent){
  selectImpl(prompt,callbackMethod,file,callbackObject,parent,FileDialog.LOAD,null);
}
