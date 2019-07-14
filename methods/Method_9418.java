private String getCurrentValues(){
  StringBuilder values=new StringBuilder();
  for (int a=0; a < inputFields.length; a++) {
    values.append(inputFields[a].getText()).append(",");
  }
  if (inputExtraFields != null) {
    for (int a=0; a < inputExtraFields.length; a++) {
      values.append(inputExtraFields[a].getText()).append(",");
    }
  }
  for (int a=0, count=documents.size(); a < count; a++) {
    values.append(documents.get(a).secureFile.id);
  }
  if (frontDocument != null) {
    values.append(frontDocument.secureFile.id);
  }
  if (reverseDocument != null) {
    values.append(reverseDocument.secureFile.id);
  }
  if (selfieDocument != null) {
    values.append(selfieDocument.secureFile.id);
  }
  for (int a=0, count=translationDocuments.size(); a < count; a++) {
    values.append(translationDocuments.get(a).secureFile.id);
  }
  return values.toString();
}
