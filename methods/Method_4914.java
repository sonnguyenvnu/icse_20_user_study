/** 
 * Returns start position of period in renderer time. 
 */
public long getStartPositionRendererTime(){
  return info.startPositionUs + rendererPositionOffsetUs;
}
