public void checkSavedPassword(){
  if (savedSaltedPassword == null && savedPasswordHash == null || Math.abs(SystemClock.elapsedRealtime() - savedPasswordTime) < 30 * 60 * 1000) {
    return;
  }
  resetSavedPassword();
}
