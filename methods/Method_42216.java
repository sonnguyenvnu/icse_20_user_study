@RequiresApi(api=Build.VERSION_CODES.M) public static boolean checkFinger(Context ctx){
  KeyguardManager keyguardManager=(KeyguardManager)ctx.getSystemService(KEYGUARD_SERVICE);
  FingerprintManager fingerprintManager=(FingerprintManager)ctx.getSystemService(FINGERPRINT_SERVICE);
  try {
    if (!fingerprintManager.isHardwareDetected()) {
      StringUtils.showToast(ctx,ctx.getString(R.string.fp_not_supported));
      return false;
    }
    if (!fingerprintManager.hasEnrolledFingerprints()) {
      StringUtils.showToast(ctx,ctx.getString(R.string.fp_not_configured));
      return false;
    }
    if (!keyguardManager.isKeyguardSecure()) {
      StringUtils.showToast(ctx,ctx.getString(R.string.fp_not_enabled_sls));
      return false;
    }
  }
 catch (  SecurityException se) {
    se.printStackTrace();
  }
  return true;
}
