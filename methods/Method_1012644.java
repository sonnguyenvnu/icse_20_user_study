/** 
 * Searches for a renderer of this name connected at the given address.
 * @param name the renderer name.
 * @param ia the address.
 * @return the matching renderer or null.
 */
public static RendererConfiguration find(String name,InetAddress ia){
  for (  RendererConfiguration r : getConnectedRenderersConfigurations()) {
    if (ia.equals(r.getAddress()) && name.equals(r.getConfName())) {
      return r;
    }
  }
  return null;
}
