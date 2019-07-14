/** 
 * Generates for each ruleset a page. The page contains the details for each rule.
 * @param rulesets all rulesets
 * @throws IOException
 */
private void generateRuleSetIndex(Map<Language,List<RuleSet>> rulesets) throws IOException {
  for (  Map.Entry<Language,List<RuleSet>> entry : rulesets.entrySet()) {
    Language language=entry.getKey();
    String languageTersename=language.getTerseName();
    String languageName=language.getName();
    for (    RuleSet ruleset : entry.getValue()) {
      String rulesetFilename=RuleSetUtils.getRuleSetFilename(ruleset);
      String filename=RULESET_INDEX_FILENAME_PATTERN.replace("${language.tersename}",languageTersename).replace("${ruleset.name}",rulesetFilename);
      Path path=getAbsoluteOutputPath(filename);
      String permalink=RULESET_INDEX_PERMALINK_PATTERN.replace("${language.tersename}",languageTersename).replace("${ruleset.name}",rulesetFilename);
      String ruleSetSourceFilepath="../" + getRuleSetSourceFilepath(ruleset);
      List<String> lines=new LinkedList<>();
      lines.add("---");
      lines.add("title: " + ruleset.getName());
      lines.add("summary: " + getRuleSetDescriptionSingleLine(ruleset));
      lines.add("permalink: " + permalink);
      lines.add("folder: pmd/rules/" + languageTersename);
      lines.add("sidebaractiveurl: /" + LANGUAGE_INDEX_PERMALINK_PATTERN.replace("${language.tersename}",languageTersename));
      lines.add("editmepath: " + ruleSetSourceFilepath);
      lines.add("keywords: " + getRuleSetKeywords(ruleset));
      lines.add("language: " + languageName);
      lines.add("---");
      lines.add(GENERATED_WARNING.replace("${source}",ruleSetSourceFilepath));
      for (      Rule rule : getSortedRules(ruleset)) {
        lines.add("## " + rule.getName());
        lines.add("");
        if (rule instanceof RuleReference) {
          RuleReference ref=(RuleReference)rule;
          if (ruleset.getFileName().equals(ref.getRuleSetReference().getRuleSetFileName())) {
            lines.add(DEPRECATION_LABEL);
            lines.add("");
            lines.add("This rule has been renamed. Use instead: [" + ref.getRule().getName() + "](" + "#" + ref.getRule().getName().toLowerCase(Locale.ROOT) + ")");
            lines.add("");
          }
 else {
            String otherLink=RULESET_INDEX_PERMALINK_PATTERN.replace("${language.tersename}",languageTersename).replace("${ruleset.name}",RuleSetUtils.getRuleSetFilename(ref.getRuleSetReference().getRuleSetFileName()));
            lines.add(DEPRECATION_LABEL);
            lines.add("");
            lines.add("The rule has been moved to another ruleset. Use instead: [" + ref.getRule().getName() + "](" + otherLink + "#" + ref.getRule().getName().toLowerCase(Locale.ROOT) + ")");
            lines.add("");
          }
        }
        if (rule.isDeprecated()) {
          lines.add(DEPRECATION_LABEL);
          lines.add("");
        }
        if (rule.getSince() != null) {
          lines.add("**Since:** PMD " + rule.getSince());
          lines.add("");
        }
        lines.add("**Priority:** " + rule.getPriority() + " (" + rule.getPriority().getPriority() + ")");
        lines.add("");
        if (rule.getMinimumLanguageVersion() != null) {
          lines.add("**Minimum Language Version:** " + rule.getLanguage().getName() + " " + rule.getMinimumLanguageVersion().getVersion());
          lines.add("");
        }
        lines.addAll(EscapeUtils.escapeLines(toLines(stripIndentation(rule.getDescription()))));
        lines.add("");
        if (rule instanceof XPathRule || rule instanceof RuleReference && ((RuleReference)rule).getRule() instanceof XPathRule) {
          lines.add("**This rule is defined by the following XPath expression:**");
          lines.add("``` xpath");
          lines.addAll(toLines(StringUtils.stripToEmpty(rule.getProperty(XPathRule.XPATH_DESCRIPTOR))));
          lines.add("```");
        }
 else {
          lines.add("**This rule is defined by the following Java class:** " + "[" + rule.getRuleClass() + "](" + GITHUB_SOURCE_LINK + getRuleClassSourceFilepath(rule.getRuleClass()) + ")");
        }
        lines.add("");
        if (!rule.getExamples().isEmpty()) {
          lines.add("**Example(s):**");
          lines.add("");
          for (          String example : rule.getExamples()) {
            lines.add("``` " + mapLanguageForHighlighting(languageTersename));
            lines.addAll(toLines(StringUtils.stripToEmpty(example)));
            lines.add("```");
            lines.add("");
          }
        }
        List<PropertyDescriptor<?>> properties=new ArrayList<>(rule.getPropertyDescriptors());
        properties.remove(Rule.VIOLATION_SUPPRESS_REGEX_DESCRIPTOR);
        properties.remove(Rule.VIOLATION_SUPPRESS_XPATH_DESCRIPTOR);
        properties.remove(XPathRule.XPATH_DESCRIPTOR);
        properties.remove(XPathRule.VERSION_DESCRIPTOR);
        if (!properties.isEmpty()) {
          lines.add("**This rule has the following properties:**");
          lines.add("");
          lines.add("|Name|Default Value|Description|Multivalued|");
          lines.add("|----|-------------|-----------|-----------|");
          for (          PropertyDescriptor<?> propertyDescriptor : properties) {
            String description=propertyDescriptor.description();
            final boolean isDeprecated=isDeprecated(propertyDescriptor);
            if (isDeprecated) {
              description=description.substring(DEPRECATED_RULE_PROPERTY_MARKER.length());
            }
            String defaultValue=determineDefaultValueAsString(propertyDescriptor,rule,true);
            String multiValued="no";
            if (propertyDescriptor.isMultiValue()) {
              MultiValuePropertyDescriptor<?> multiValuePropertyDescriptor=(MultiValuePropertyDescriptor<?>)propertyDescriptor;
              multiValued="yes. Delimiter is '" + multiValuePropertyDescriptor.multiValueDelimiter() + "'.";
            }
            lines.add("|" + EscapeUtils.escapeMarkdown(StringEscapeUtils.escapeHtml4(propertyDescriptor.name())) + "|" + EscapeUtils.escapeMarkdown(StringEscapeUtils.escapeHtml4(defaultValue)) + "|" + EscapeUtils.escapeMarkdown((isDeprecated ? DEPRECATION_LABEL_SMALL : "") + StringEscapeUtils.escapeHtml4(description)) + "|" + EscapeUtils.escapeMarkdown(StringEscapeUtils.escapeHtml4(multiValued)) + "|");
          }
          lines.add("");
        }
        if (properties.isEmpty()) {
          lines.add("**Use this rule by referencing it:**");
        }
 else {
          lines.add("**Use this rule with the default properties by just referencing it:**");
        }
        lines.add("``` xml");
        lines.add("<rule ref=\"category/" + languageTersename + "/" + rulesetFilename + ".xml/" + rule.getName() + "\" />");
        lines.add("```");
        lines.add("");
        if (properties.stream().anyMatch(it -> !isDeprecated(it))) {
          lines.add("**Use this rule and customize it:**");
          lines.add("``` xml");
          lines.add("<rule ref=\"category/" + languageTersename + "/" + rulesetFilename + ".xml/" + rule.getName() + "\">");
          lines.add("    <properties>");
          for (          PropertyDescriptor<?> propertyDescriptor : properties) {
            if (!isDeprecated(propertyDescriptor)) {
              String defaultValue=determineDefaultValueAsString(propertyDescriptor,rule,false);
              lines.add("        <property name=\"" + propertyDescriptor.name() + "\" value=\"" + defaultValue + "\" />");
            }
          }
          lines.add("    </properties>");
          lines.add("</rule>");
          lines.add("```");
          lines.add("");
        }
      }
      writer.write(path,lines);
      System.out.println("Generated " + path);
    }
  }
}
