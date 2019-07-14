String parsePathForName(String path,OpenMode openmode){
  Resources resources=getActivity().getResources();
  if ("/".equals(path)) {
    return resources.getString(R.string.rootdirectory);
  }
 else   if (openmode == OpenMode.SMB && path.startsWith("smb:/")) {
    return (new File(parseSmbPath(path)).getName());
  }
 else   if ("/storage/emulated/0".equals(path)) {
    return resources.getString(R.string.internalstorage);
  }
 else   if (openmode == OpenMode.CUSTOM) {
    return new MainActivityHelper(mainActivity).getIntegralNames(path);
  }
 else {
    return new File(path).getName();
  }
}
