public int checksum(){
  int retval=0;
synchronized (array) {
    for (int i=0; i < num_fields; i++)     for (int j=0; j < num_fields; j++)     retval+=array[i][j];
  }
  return retval;
}
