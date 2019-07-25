@NotNull public String version(){
  StringBuilder version=new StringBuilder(major);
  if (minor != null) {
    version.append('.').append(minor);
  }
  if (patch != null) {
    version.append('.').append(patch);
  }
  if (pre != null) {
    version.append('-').append(pre);
  }
  if (build != null) {
    version.append('+').append(build);
  }
  return version.toString();
}
