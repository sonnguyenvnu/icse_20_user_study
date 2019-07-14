static public Change load(LineNumberReader reader,Pool pool) throws Exception {
  String line=reader.readLine();
  if (line == null) {
    line="";
  }
  int equal=line.indexOf('=');
  assert "count".equals(line.substring(0,equal));
  int count=Integer.parseInt(line.substring(equal + 1));
  Change[] changes=new Change[count];
  for (int i=0; i < count; i++) {
    changes[i]=History.readOneChange(reader,pool);
  }
  line=reader.readLine();
  assert "/ec/".equals(line);
  return new ChangeSequence(changes);
}
