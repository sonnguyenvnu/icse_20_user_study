public boolean isFingerprintSupported(){
  if (!fingerprintManager.isHardwareDetected()) {
    fingerprintSupported=false;
  }
  if (ActivityCompat.checkSelfPermission(context,Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
    fingerprintSupported=false;
  }
  if (!fingerprintManager.hasEnrolledFingerprints()) {
    fingerprintSupported=false;
  }
  if (!keyguardManager.isKeyguardSecure()) {
    fingerprintSupported=false;
  }
  return fingerprintSupported;
}
