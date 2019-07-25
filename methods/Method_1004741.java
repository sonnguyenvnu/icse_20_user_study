/** 
 * Adds an entry to the bulk request, potentially flushing if the request reaches capacity.
 * @param payload the entire bulk entry in JSON format, including the header and payload.
 */
public void add(BytesRef payload){
  if (payload.length() > ba.available()) {
    if (autoFlush) {
      flush();
    }
 else {
      throw new EsHadoopIllegalStateException(String.format("Auto-flush disabled and bulk buffer full; disable manual flush or increase " + "capacity [current size %s]; bailing out",ba.capacity()));
    }
  }
  data.copyFrom(payload);
  dataEntries++;
  if (bufferEntriesThreshold > 0 && dataEntries >= bufferEntriesThreshold) {
    if (autoFlush) {
      flush();
    }
 else {
      if (dataEntries > bufferEntriesThreshold) {
        throw new EsHadoopIllegalStateException(String.format("Auto-flush disabled and maximum number of entries surpassed; disable manual " + "flush or increase capacity [current size %s]; bailing out",bufferEntriesThreshold));
      }
    }
  }
}
