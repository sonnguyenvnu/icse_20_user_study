private String getPemContents(){
  if (selectedPem == null)   return null;
  try {
    BufferedReader reader=new BufferedReader(new InputStreamReader(context.getContentResolver().openInputStream(selectedPem)));
    StringBuilder sb=new StringBuilder();
    for (String line=reader.readLine(); line != null; line=reader.readLine()) {
      sb.append(line).append("\n");
    }
    return sb.toString();
  }
 catch (  FileNotFoundException e) {
    return null;
  }
catch (  IOException e) {
    return null;
  }
}
