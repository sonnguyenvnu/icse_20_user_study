@Override public String getMessage(){
  StringBuilder sb=new StringBuilder(String.format("There were %d errors:",fErrors.size()));
  for (  Throwable e : fErrors) {
    sb.append(String.format("%n  %s(%s)",e.getClass().getName(),e.getMessage()));
  }
  return sb.toString();
}
