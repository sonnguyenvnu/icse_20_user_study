@Override public String toString(){
  if (message != null) {
    return (sha != null && sha.length() > 7 ? sha.substring(0,7) + " - " : "") + message.split(System.lineSeparator())[0];
  }
 else   if (sha != null && sha.length() > 10) {
    return sha.substring(0,10);
  }
  return "N/A";
}
