public void retry(){
  boolean success=false;
  if (!processors.isEmpty()) {
    processors.forEach((pid,p) -> {
      if (p.state == SavePointState.WAIT) {
        retry(pid);
        return;
      }
    }
);
    success=true;
  }
  if (!success) {
    throw new RuntimeException("Failed to retry");
  }
}
