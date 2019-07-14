@Override public synchronized void onItemSelected(AdapterView<?> parent,View view,int pos,long id){
  selectedModel=parent.getItemAtPosition(pos).toString();
  Log.d(TAG,"Selected model: " + selectedModel);
  preview.stop();
  if (allPermissionsGranted()) {
    createCameraSource(selectedModel);
    startCameraSource();
  }
 else {
    getRuntimePermissions();
  }
}
