@Override public boolean canHandle(@NonNull File file) throws IOException {
  BufferedReader reader=new BufferedReader(new FileReader(file));
  String line=reader.readLine();
  return line.startsWith("HabitName,HabitDescription,HabitCategory");
}
