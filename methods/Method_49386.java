private static CompactionOptions<?> compactionOptions(final Configuration configuration){
  if (!configuration.has(COMPACTION_STRATEGY)) {
    return null;
  }
  final CompactionOptions<?> compactionOptions=Match(configuration.get(COMPACTION_STRATEGY)).of(Case($("SizeTieredCompactionStrategy"),sizedTieredStategy()),Case($("DateTieredCompactionStrategy"),dateTieredStrategy()),Case($("LeveledCompactionStrategy"),leveledStrategy()));
  Array.of(configuration.get(COMPACTION_OPTIONS)).grouped(2).forEach(keyValue -> compactionOptions.freeformOption(keyValue.get(0),keyValue.get(1)));
  return compactionOptions;
}
