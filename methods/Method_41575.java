@Override public String toString(){
  Throwable cause=getUnderlyingException();
  if (cause == null || cause == this) {
    return super.toString();
  }
 else {
    return super.toString() + " [See nested exception: " + cause + "]";
  }
}
