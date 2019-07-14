/** 
 * Writes the first row, containing header information, using the given writer. This consists of the date title and the names of the selected habits.
 * @param out the writer to use
 * @throws IOException if there was a problem writing
 */
private void writeMultipleHabitsHeader(Writer out) throws IOException {
  out.write("Date" + DELIMITER);
  for (  Habit h : selectedHabits) {
    out.write(h.getName());
    out.write(DELIMITER);
  }
  out.write("\n");
}
