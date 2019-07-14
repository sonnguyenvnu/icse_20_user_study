public void startActivityForResult(@NonNull String instanceId,@NonNull Intent intent,int requestCode){
  registerForActivityResult(instanceId,requestCode);
  startActivityForResult(intent,requestCode);
}
