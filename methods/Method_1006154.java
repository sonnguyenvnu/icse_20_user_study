@Override public void export(final BibDatabaseContext databaseContext,final Path resultFile,final Charset encoding,List<BibEntry> entries) throws SaveException {
  Objects.requireNonNull(databaseContext);
  Objects.requireNonNull(entries);
  if (entries.isEmpty()) {
    return;
  }
  File file=new File();
  for (  BibEntry bibEntry : entries) {
    Entry entry=new Entry();
    bibEntry.getCiteKeyOptional().ifPresent(entry::setId);
    String type=bibEntry.getType().toLowerCase(ENGLISH);
switch (type) {
case "article":
      parse(new Article(),bibEntry,entry);
    break;
case "book":
  parse(new Book(),bibEntry,entry);
break;
case "booklet":
parse(new Booklet(),bibEntry,entry);
break;
case "conference":
parse(new Conference(),bibEntry,entry);
break;
case "inbook":
parseInbook(new Inbook(),bibEntry,entry);
break;
case "incollection":
parse(new Incollection(),bibEntry,entry);
break;
case "inproceedings":
parse(new Inproceedings(),bibEntry,entry);
break;
case "mastersthesis":
parse(new Mastersthesis(),bibEntry,entry);
break;
case "manual":
parse(new Manual(),bibEntry,entry);
break;
case "misc":
parse(new Misc(),bibEntry,entry);
break;
case "phdthesis":
parse(new Phdthesis(),bibEntry,entry);
break;
case "proceedings":
parse(new Proceedings(),bibEntry,entry);
break;
case "techreport":
parse(new Techreport(),bibEntry,entry);
break;
case "unpublished":
parse(new Unpublished(),bibEntry,entry);
break;
default :
LOGGER.warn("unexpected type appeared");
break;
}
file.getEntry().add(entry);
}
createMarshallerAndWriteToFile(file,resultFile);
}
