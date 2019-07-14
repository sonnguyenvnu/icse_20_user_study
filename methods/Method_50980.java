@Override public String toStringLong(){
  if (ClassLoaderUtil.CLINIT.equals(name)) {
    return name;
  }
 else {
    return super.toStringLong();
  }
}
