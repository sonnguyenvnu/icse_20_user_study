private static String getPmdPropertiesURL(){
  String url="https://pmd.github.io/pmd-" + PMDVersion.VERSION + "/pmd_devdocs_working_with_properties.html";
  if (PMDVersion.isSnapshot() || PMDVersion.isUnknown()) {
    url="https://pmd.github.io/latest/pmd_devdocs_working_with_properties.html";
  }
  return url;
}
