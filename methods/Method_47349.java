@RequiresApi(api=Build.VERSION_CODES.M) public void authenticate(FingerprintManager manager,FingerprintManager.CryptoObject cryptoObject){
  CancellationSignal cancellationSignal=new CancellationSignal();
  if (ActivityCompat.checkSelfPermission(context,Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
    return;
  }
  manager.authenticate(cryptoObject,cancellationSignal,0,this,null);
}
