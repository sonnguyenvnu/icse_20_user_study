/** 
 * Manually replaces the manifest  {@link Uri}.
 * @param manifestUri The replacement manifest {@link Uri}.
 */
public void replaceManifestUri(Uri manifestUri){
synchronized (manifestUriLock) {
    this.manifestUri=manifestUri;
    this.initialManifestUri=manifestUri;
  }
}
