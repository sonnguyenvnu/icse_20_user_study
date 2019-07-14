private static int processFile(File inputFile,File outputFile) throws IOException {
  BufferedReader in=null;
  PrintStream out=null;
  int inputLines=0;
  int replacements=0;
  int parseErrors=0;
  try {
    in=new BufferedReader(new FileReader(inputFile));
    out=new PrintStream(outputFile);
    String line;
    while (null != (line=in.readLine())) {
      inputLines++;
      Matcher m=REPLACEMENT_PATTERN.matcher(line);
      if (m.matches()) {
        String cfgKey=m.group(2).trim();
        m.group(3);
        try {
          ConfigElement.PathIdentifier pid=ConfigElement.parse(GraphDatabaseConfiguration.ROOT_NS,cfgKey);
          ConfigOption<?> opt=(ConfigOption<?>)pid.element;
          String kvPair=m.group(1);
          String description="# " + WordUtils.wrap(opt.getDescription(),WRAP_COLUMNS,"\n# ",false);
          String dt="# Data Type:  ";
          if (opt.getDatatype().isArray()) {
            dt+=opt.getDatatype().getComponentType().toString() + "[]";
          }
 else           if (opt.getDatatype().isEnum()) {
            Enum[] enums=(Enum[])opt.getDatatype().getEnumConstants();
            String[] names=new String[enums.length];
            for (int i=0; i < names.length; i++)             names[i]=enums[i].name();
            dt+=opt.getDatatype().getSimpleName() + " enum:";
            String s="\n#             " + "{ " + Joiner.on(", ").join(names) + " }";
            dt+=WordUtils.wrap(s,WRAP_COLUMNS,"\n#               ",false);
          }
 else {
            dt+=opt.getDatatype().getSimpleName();
          }
          String defaultValue="# Default:    ";
          if (null == opt.getDefaultValue()) {
            defaultValue+="(no default value)";
          }
 else           if (opt.getDatatype().isArray()) {
            defaultValue+=Joiner.on(", ").join((Object[])opt.getDefaultValue());
          }
 else           if (opt.getDatatype().isEnum()) {
            defaultValue+=((Enum)opt.getDefaultValue()).name();
          }
 else {
            defaultValue+=opt.getDefaultValue();
          }
          String mut="# Mutability: " + opt.getType();
          if (opt.isManaged()) {
            mut+="\n#\n# ";
            if (opt.getType().equals(ConfigOption.Type.FIXED)) {
              mut+="This setting is " + opt.getType() + " and cannot be changed after bootstrapping JanusGraph.";
            }
 else {
              final String warning="Settings with mutability " + opt.getType() + " are centrally managed in " + "JanusGraph's storage backend.  After starting the database for the first time, " + "this file's copy of this setting is ignored.  Use JanusGraph's Management " + "System to read or modify this value after bootstrapping.";
              mut+=WordUtils.wrap(warning,WRAP_COLUMNS,"\n# ",false);
            }
          }
          out.println(description);
          out.println("#");
          out.println(defaultValue);
          out.println(dt);
          out.println(mut);
          out.println(kvPair);
          replacements++;
        }
 catch (        RuntimeException e) {
          out.println(line);
          log.warn("Exception on {}:{}",inputFile,line,e);
          parseErrors++;
        }
      }
 else {
        out.println(line);
      }
    }
    log.info("Read {}: {} lines, {} macro substitutions",inputFile,inputLines,replacements);
  }
  finally {
    IOUtils.closeQuietly(out);
    IOUtils.closeQuietly(in);
  }
  ConfigurationLint.Status stat=ConfigurationLint.validate(outputFile.getAbsolutePath());
  if (0 != stat.getErrorSettingCount())   log.error("Output file {} failed to validate",outputFile);
  parseErrors+=stat.getErrorSettingCount();
  return parseErrors;
}
