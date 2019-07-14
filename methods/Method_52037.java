/** 
 * Gets the next available index based on a key and a histogram (map of keys to int counters). If the key doesn't exist, we add a new entry with the startIndex. <p>Used for lambda and anonymous class counters
 * @param histogram  The histogram map
 * @param key        The key to access
 * @param startIndex First index given out when the key doesn't exist
 * @return The next free index
 */
private static <T>int getNextIndexFromHistogram(Map<T,Integer> histogram,T key,int startIndex){
  Integer count=histogram.get(key);
  if (count == null) {
    histogram.put(key,startIndex);
    return startIndex;
  }
 else {
    histogram.put(key,count + 1);
    return count + 1;
  }
}
