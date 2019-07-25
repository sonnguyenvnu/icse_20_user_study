public static TypeData[] make(int size){
  TypeData[] array=new TypeData[size];
  for (int i=0; i < size; i++)   array[i]=TypeTag.TOP;
  return array;
}
