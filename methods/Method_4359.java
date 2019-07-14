public void onProvisionCompleted(){
  if (openInternal(false)) {
    doLicense(true);
  }
}
