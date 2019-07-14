@JsonIgnore public boolean isEmpty(){
  for (  Cell cell : cells) {
    if (cell != null && cell.value != null && !isValueBlank(cell.value)) {
      return false;
    }
  }
  return true;
}
