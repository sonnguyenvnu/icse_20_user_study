/** 
 * If there is a loading period, reevaluates its buffer.
 * @param rendererPositionUs The current renderer position.
 */
public void reevaluateBuffer(long rendererPositionUs){
  if (loading != null) {
    loading.reevaluateBuffer(rendererPositionUs);
  }
}
