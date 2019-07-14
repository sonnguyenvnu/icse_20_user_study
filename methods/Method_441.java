public String getInput(){
  if (input instanceof char[]) {
    return new String((char[])input);
  }
  return input.toString();
}
