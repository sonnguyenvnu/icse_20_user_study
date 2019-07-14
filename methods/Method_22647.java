static WinLibC getLibC(){
  if (clib == null) {
    try {
      clib=(WinLibC)Native.loadLibrary("msvcrt",WinLibC.class);
    }
 catch (    UnsatisfiedLinkError ule) {
      Messages.showTrace("JNA Error","JNA could not be loaded. Please report here:\n" + "http://github.com/processing/processing/issues/new",ule,true);
    }
  }
  return clib;
}
