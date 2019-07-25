/** 
 * Tries to recover model after an abnormal TLC termination It deletes all temporary files on disk and restores the state to unlocked.
 */
public void recover(){
  final IFile resource=getFile();
  if (resource.exists()) {
    try {
      IMarker[] foundMarkers=resource.findMarkers(TLC_CRASHED_MARKER,false,IResource.DEPTH_ZERO);
      if (foundMarkers.length == 0) {
        return;
      }
      for (int i=0; i < foundMarkers.length; i++) {
        foundMarkers[i].delete();
      }
      foundMarkers=resource.findMarkers(TLC_MODEL_IN_USE_MARKER,false,IResource.DEPTH_ZERO);
      for (int i=0; i < foundMarkers.length; i++) {
        foundMarkers[i].delete();
      }
    }
 catch (    CoreException shouldNotHappen) {
      TLCActivator.logError(shouldNotHappen.getMessage(),shouldNotHappen);
    }
  }
}
