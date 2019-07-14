/** 
 * Puts the given entry in the  {@link #entries} hash set. This method does <i>not</i> checkwhether  {@link #entries} already contains a similar entry or not. {@link #entries} is resizedif necessary to avoid hash collisions (multiple entries needing to be stored at the same  {@link #entries} array index) as much as possible, with reasonable memory usage.
 * @param entry an Entry (which must not already be contained in {@link #entries}).
 * @return the given entry
 */
private Entry put(final Entry entry){
  if (entryCount > (entries.length * 3) / 4) {
    int currentCapacity=entries.length;
    int newCapacity=currentCapacity * 2 + 1;
    Entry[] newEntries=new Entry[newCapacity];
    for (int i=currentCapacity - 1; i >= 0; --i) {
      Entry currentEntry=entries[i];
      while (currentEntry != null) {
        int newCurrentEntryIndex=currentEntry.hashCode % newCapacity;
        Entry nextEntry=currentEntry.next;
        currentEntry.next=newEntries[newCurrentEntryIndex];
        newEntries[newCurrentEntryIndex]=currentEntry;
        currentEntry=nextEntry;
      }
    }
    entries=newEntries;
  }
  entryCount++;
  int index=entry.hashCode % entries.length;
  entry.next=entries[index];
  return entries[index]=entry;
}
