/** 
 * Build the diagnostic message. <p>Examples: <ul> <li>Non-exhaustive switch, expected cases for: FOO <li>Non-exhaustive switch, expected cases for: FOO, BAR, BAZ, and 42 others. </ul>
 */
private String buildMessage(Set<String> unhandled){
  StringBuilder message=new StringBuilder("Non-exhaustive switch; either add a default or handle the remaining cases: ");
  int numberToShow=unhandled.size() > MAX_CASES_TO_PRINT ? 3 : unhandled.size();
  message.append(unhandled.stream().limit(numberToShow).collect(Collectors.joining(", ")));
  if (numberToShow < unhandled.size()) {
    message.append(String.format(", and %d others",unhandled.size() - numberToShow));
  }
  return message.toString();
}
