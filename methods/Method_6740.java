public boolean addEntitiesToText(CharSequence text,boolean photoViewer,boolean useManualParse){
  return addEntitiesToText(text,messageOwner.entities,isOutOwner(),type,true,photoViewer,useManualParse);
}
