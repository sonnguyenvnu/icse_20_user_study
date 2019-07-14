private void memcache(PrintStream writer,List<String> args) throws DumpException {
  CountingMemoryCacheInspector.DumpInfo<CacheKey,CloseableImage> dumpInfo=mBitmapMemoryCacheInspector.dumpCacheContent();
  try {
    writer.println(mBitmapMemoryCacheInspector.getClass().getSimpleName());
    writer.println();
    writer.println("Params:");
    writer.println(formatStrLocaleSafe("Max size:          %7.2fMB",dumpInfo.maxSize / (1024.0 * KB)));
    writer.println(formatStrLocaleSafe("Max entries count: %9d",dumpInfo.maxEntriesCount));
    writer.println(formatStrLocaleSafe("Max entry size:    %7.2fMB",dumpInfo.maxEntrySize / (1024.0 * KB)));
    writer.println();
    writer.println("Summary of current content:");
    writer.println(formatStrLocaleSafe("Total size:        %7.2fMB (includes in-use content)",dumpInfo.size / (1024.0 * KB)));
    writer.println(formatStrLocaleSafe("Entries count:     %9d",dumpInfo.lruEntries.size() + dumpInfo.sharedEntries.size()));
    writer.println(formatStrLocaleSafe("LRU size:          %7.2fMB",dumpInfo.lruSize / (1024.0 * KB)));
    writer.println(formatStrLocaleSafe("LRU count:         %9d",dumpInfo.lruEntries.size()));
    writer.println(formatStrLocaleSafe("Shared size:       %7.2fMB",(dumpInfo.size - dumpInfo.lruSize) / (1024.0 * KB)));
    writer.println(formatStrLocaleSafe("Shared count:      %9d",dumpInfo.sharedEntries.size()));
    writer.println();
    writer.println("The cache consists of two parts: Things " + "currently being used and things not.");
    writer.println("Those things that are *not* currently being used are in the LRU.");
    writer.println("Things currently being used are considered to be shared. They will be added");
    writer.println("to the LRU if/when they stop being used.");
    writer.println();
    writer.println("LRU contents: (things near the top will be evicted first)");
    for (    CountingMemoryCacheInspector.DumpInfoEntry entry : dumpInfo.lruEntries) {
      writeCacheEntry(writer,entry);
    }
    writer.println();
    writer.println("Shared contents:");
    for (    CountingMemoryCacheInspector.DumpInfoEntry entry : dumpInfo.sharedEntries) {
      writeCacheEntry(writer,entry);
    }
    if (!args.isEmpty() && "-g".equals(args.get(0))) {
      getFiles(writer,dumpInfo);
    }
  }
 catch (  IOException e) {
    throw new DumpException(e.getMessage());
  }
 finally {
    dumpInfo.release();
  }
}
