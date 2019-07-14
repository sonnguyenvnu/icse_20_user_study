@Override public boolean covers(JavaFieldSignature sig){
  return super.covers(sig) && (coverFinal || !sig.isFinal) && (coverStatic || !sig.isStatic);
}
