public static EntryFormatter _XXXXX_(String opt,AbstractConfiguration conf){
  EntryFormatter formatter;
  if ("hex".equals(opt)) {
    formatter=new HexDumpEntryFormatter();
  }
 else   if ("string".equals(opt)) {
    formatter=new StringEntryFormatter();
  }
 else {
    LOG.warn("specified unexpected entryformat {}, so default EntryFormatter is used",opt);
    formatter=newEntryFormatter(conf);
  }
  return formatter;
}