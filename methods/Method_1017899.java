@Override public boolean matches(String major,String minor,String point){
  return Objects.equals(getMajorVersion(),major) && Objects.equals(getMinorVersion(),minor) && Objects.equals(getPointVersion(),point);
}
