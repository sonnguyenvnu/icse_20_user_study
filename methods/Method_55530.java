public static MacOSXLibrary create(String name){
  return name.endsWith(".framework") ? MacOSXLibraryBundle.create(name) : new MacOSXLibraryDL(name);
}
