/** 
 * Dump debug data for this page.
 * @param buff append buffer
 */
protected void dump(StringBuilder buff){
  buff.append("id: ").append(System.identityHashCode(this)).append('\n');
  buff.append("pos: ").append(Long.toHexString(pos)).append('\n');
  if (isSaved()) {
    int chunkId=DataUtils.getPageChunkId(pos);
    buff.append("chunk: ").append(Long.toHexString(chunkId)).append('\n');
  }
}
