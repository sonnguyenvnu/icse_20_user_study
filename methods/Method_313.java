/** 
 * ?????
 * @param file
 * @throws IOException
 */
private static void sortAndFindKeyWords(File file) throws IOException {
  content=Files.readLines(file,Charset.defaultCharset());
  LOGGER.info(String.valueOf(content));
  for (  String value : content) {
    boolean flag=value.contains(KEYWORD);
    if (!flag) {
      continue;
    }
    if (countMap.containsKey(value)) {
      countMap.put(value,countMap.get(value) + 1);
    }
 else {
      countMap.put(value,1);
    }
  }
  for (  String key : countMap.keySet()) {
    SortString sort=new SortString();
    sort.setKey(key);
    sort.setCount(countMap.get(key));
    contentSet.add(sort);
  }
}
