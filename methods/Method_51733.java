/** 
 * Writes for each language an index file, which lists the rulesets, the rules and links to the ruleset pages.
 * @param rulesets all registered/built-in rulesets
 * @param sortedAdditionalRulesets additional rulesets
 * @throws IOException
 */
private void generateLanguageIndex(Map<Language,List<RuleSet>> rulesets,Map<Language,List<RuleSet>> sortedAdditionalRulesets) throws IOException {
  for (  Map.Entry<Language,List<RuleSet>> entry : rulesets.entrySet()) {
    String languageTersename=entry.getKey().getTerseName();
    String filename=LANGUAGE_INDEX_FILENAME_PATTERN.replace("${language.tersename}",languageTersename);
    Path path=getAbsoluteOutputPath(filename);
    List<String> lines=new LinkedList<>();
    lines.add("---");
    lines.add("title: " + entry.getKey().getName() + " Rules");
    lines.add("tags: [rule_references, " + languageTersename + "]");
    lines.add("summary: Index of all built-in rules available for " + entry.getKey().getName());
    lines.add("language_name: " + entry.getKey().getName());
    lines.add("permalink: " + LANGUAGE_INDEX_PERMALINK_PATTERN.replace("${language.tersename}",languageTersename));
    lines.add("folder: pmd/rules");
    lines.add("---");
    lines.add(GENERATED_WARNING_NO_SOURCE);
    for (    RuleSet ruleset : entry.getValue()) {
      lines.add("## " + ruleset.getName());
      lines.add("");
      lines.add("{% include callout.html content=\"" + getRuleSetDescriptionSingleLine(ruleset) + "\" %}");
      lines.add("");
      for (      Rule rule : getSortedRules(ruleset)) {
        String link=RULESET_INDEX_PERMALINK_PATTERN.replace("${language.tersename}",languageTersename).replace("${ruleset.name}",RuleSetUtils.getRuleSetFilename(ruleset));
        if (rule instanceof RuleReference) {
          RuleReference ref=(RuleReference)rule;
          if (ruleset.getFileName().equals(ref.getRuleSetReference().getRuleSetFileName())) {
            lines.add("*   [" + rule.getName() + "](" + link + "#" + rule.getName().toLowerCase(Locale.ROOT) + "): " + DEPRECATION_LABEL_SMALL + "The rule has been renamed. Use instead " + "[" + ref.getRule().getName() + "](" + link + "#" + ref.getRule().getName().toLowerCase(Locale.ROOT) + ").");
          }
 else {
            String otherLink=RULESET_INDEX_PERMALINK_PATTERN.replace("${language.tersename}",languageTersename).replace("${ruleset.name}",RuleSetUtils.getRuleSetFilename(ref.getRuleSetReference().getRuleSetFileName()));
            lines.add("*   [" + rule.getName() + "](" + link + "#" + rule.getName().toLowerCase(Locale.ROOT) + "): " + DEPRECATION_LABEL_SMALL + "The rule has been moved to another ruleset. Use instead " + "[" + ref.getRule().getName() + "](" + otherLink + "#" + ref.getRule().getName().toLowerCase(Locale.ROOT) + ").");
          }
        }
 else {
          link+="#" + rule.getName().toLowerCase(Locale.ROOT);
          lines.add("*   [" + rule.getName() + "](" + link + "): " + (rule.isDeprecated() ? DEPRECATION_LABEL_SMALL : "") + getShortRuleDescription(rule));
        }
      }
      lines.add("");
    }
    List<RuleSet> additionalRulesetsForLanguage=sortedAdditionalRulesets.get(entry.getKey());
    if (additionalRulesetsForLanguage != null) {
      lines.add("## Additional rulesets");
      lines.add("");
      for (      RuleSet ruleset : additionalRulesetsForLanguage) {
        boolean deprecated=RuleSetUtils.isRuleSetDeprecated(ruleset);
        String rulesetName=ruleset.getName() + " (`" + RuleSetUtils.getRuleSetClasspath(ruleset) + "`)";
        if (!deprecated) {
          lines.add("*   " + rulesetName + ":");
          lines.add("");
          lines.add("    " + getRuleSetDescriptionSingleLine(ruleset));
          lines.add("");
        }
 else {
          lines.add("*   " + rulesetName + ":");
          lines.add("");
          lines.add("    " + DEPRECATION_LABEL_SMALL + " This ruleset is for backwards compatibility.");
          lines.add("");
        }
        lines.add("    It contains the following rules:");
        lines.add("");
        StringBuilder rules=new StringBuilder();
        for (        Rule rule : getSortedRules(ruleset)) {
          if (rules.length() == 0) {
            rules.append("    ");
          }
 else {
            rules.append(", ");
          }
          Rule resolvedRule=RuleSetUtils.resolveRuleReferences(rule);
          if (resolvedRule instanceof RuleReference) {
            RuleReference ref=(RuleReference)resolvedRule;
            String otherLink=RULESET_INDEX_PERMALINK_PATTERN.replace("${language.tersename}",languageTersename).replace("${ruleset.name}",RuleSetUtils.getRuleSetFilename(ref.getRuleSetReference().getRuleSetFileName()));
            rules.append("[").append(ref.getName()).append("](");
            rules.append(otherLink).append("#").append(ref.getRule().getName().toLowerCase(Locale.ROOT)).append(")");
          }
 else {
            rules.append(rule.getName());
          }
        }
        lines.add(rules.toString());
        lines.add("");
      }
      lines.add("");
    }
    System.out.println("Generated " + path);
    writer.write(path,lines);
  }
}
