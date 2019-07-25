public String build(List<String> paths){
  return unionDataFrames(paths,"sqlContext.read.%s(\"%s\").toDF()\n",method);
}
