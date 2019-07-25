@Override public String format(final Map<String,? extends OptionDescriptor> options){
  final Set<OptionDescriptor> opts=new LinkedHashSet<OptionDescriptor>(options.values());
  lines.addAll(HELP_PREAMBLE);
  final int helpIndex=lines.size();
  StringBuilder sb;
  for (  final OptionDescriptor descriptor : opts) {
    if (descriptor.representsNonOptions())     continue;
    final Collection<String> names=descriptor.options();
    sb=new StringBuilder().append("    ").append(optionsToString(names));
    if (descriptor.requiresArgument())     sb.append(" uri");
    sb.append(": ").append(descriptor.description());
    if (names.contains("help"))     lines.add(helpIndex,sb.toString());
 else     lines.add(sb.toString());
  }
  lines.addAll(HELP_POST);
  return JOINER.join(lines) + LINE_SEPARATOR;
}
