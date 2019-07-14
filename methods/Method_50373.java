private List<HmilyCompensationVO> findByPage(final File[] files,final int start,final int pageSize){
  if (files != null && files.length > 0) {
    return Arrays.stream(files).skip(start).limit(pageSize).map(this::readTransaction).collect(Collectors.toList());
  }
  return null;
}
