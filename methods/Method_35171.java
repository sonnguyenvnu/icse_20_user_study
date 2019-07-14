public void startActivityForResult(@NonNull String instanceId,@NonNull Intent intent,int requestCode,@Nullable Bundle options){
  registerForActivityResult(instanceId,requestCode);
  startActivityForResult(intent,requestCode,options);
}
