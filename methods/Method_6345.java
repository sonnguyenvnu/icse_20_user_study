private static int calcDocumentsHash(ArrayList<TLRPC.Document> arrayList){
  if (arrayList == null) {
    return 0;
  }
  long acc=0;
  for (int a=0; a < Math.min(200,arrayList.size()); a++) {
    TLRPC.Document document=arrayList.get(a);
    if (document == null) {
      continue;
    }
    int high_id=(int)(document.id >> 32);
    int lower_id=(int)document.id;
    acc=((acc * 20261) + 0x80000000L + high_id) % 0x80000000L;
    acc=((acc * 20261) + 0x80000000L + lower_id) % 0x80000000L;
  }
  return (int)acc;
}
