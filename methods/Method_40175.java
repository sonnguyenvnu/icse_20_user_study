private void locateNames(List<Alias> names,int start){
  for (  Alias a : names) {
    Name first=a.name.get(0);
    start=content.indexOf(first.id,start);
    first.start=start;
    first.end=start + first.id.length();
    start=first.end;
    if (a.asname != null) {
      start=content.indexOf(a.asname.id,start);
      a.asname.start=start;
      a.asname.end=start + a.asname.id.length();
      a.asname.file=file;
      start=a.asname.end;
    }
  }
}
