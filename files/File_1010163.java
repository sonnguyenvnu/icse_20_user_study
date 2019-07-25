/*
 * Copyright 2003-2018 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jetbrains.mps.generator.impl.plan;

import jetbrains.mps.generator.runtime.TemplateMappingConfiguration;
import jetbrains.mps.generator.runtime.TemplateMappingPriorityRule;
import jetbrains.mps.generator.runtime.TemplateModel;
import jetbrains.mps.generator.runtime.TemplateModule;
import jetbrains.mps.project.structure.modules.mappingpriorities.MappingConfig_AbstractRef;
import jetbrains.mps.project.structure.modules.mappingpriorities.MappingConfig_ExternalRef;
import jetbrains.mps.project.structure.modules.mappingpriorities.MappingConfig_RefAllGlobal;
import jetbrains.mps.project.structure.modules.mappingpriorities.MappingConfig_RefAllLocal;
import jetbrains.mps.project.structure.modules.mappingpriorities.MappingConfig_RefSet;
import jetbrains.mps.project.structure.modules.mappingpriorities.MappingConfig_SimpleRef;
import jetbrains.mps.project.structure.modules.mappingpriorities.MappingPriorityRule;
import jetbrains.mps.project.structure.modules.mappingpriorities.RuleType;
import jetbrains.mps.smodel.language.LanguageRuntime;
import jetbrains.mps.util.Pair;
import jetbrains.mps.util.containers.MultiMap;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.mps.openapi.language.SLanguage;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNodeReference;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.persistence.PersistenceFacade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Igor Alshannikov
 * Date: Mar 27, 2007
 */
public class GenerationPartitioner {
  private static final Logger LOG = LogManager.getLogger(GenerationPlan.class);

  // generators
  private final Collection<TemplateModule> myGenerators;

  // maps
  private final Map<SModuleReference, TemplateModule> myModulesMap;
  private final Map<SModelReference, TemplateModel> myModelMap;

  // record dependencies between generators explicitly established by priority rules
  // use these to avoid adding implicit rules ('not later' than target language) between generators with explicit rules
   private final Set<Pair<TemplateModule, TemplateModule>> myExplicitDependencies;

  // result
  private final PartitioningSolver mySolver;
  private final PriorityConflicts myConflicts;

  public GenerationPartitioner(Collection<TemplateModule> generators) {
    myGenerators = generators;
    myConflicts = new PriorityConflicts(generators);
    mySolver = new PartitioningSolver(myConflicts);

    myModulesMap = new HashMap<>(myGenerators.size());
    myModelMap = new HashMap<>();
    for (TemplateModule module : myGenerators) {
      myModulesMap.put(module.getModuleReference(), module);
      for (TemplateModel model : module.getModels()) {
        myModelMap.put(model.getSModelReference(), model);
      }
    }
    myExplicitDependencies = new HashSet<>();
  }

  public List<List<TemplateMappingConfiguration>> createMappingSets() {
    ArrayList<TemplateMappingConfiguration> allMappingConfigurations = new ArrayList<>();
    for (TemplateModule generator : myGenerators) {
      for (TemplateModel model : generator.getModels()) {
        allMappingConfigurations.addAll(model.getConfigurations());
      }
    }

    mySolver.prepare(allMappingConfigurations);

    // get priority mapping rules from generators and build 'priority map'
    loadRules();

    // solve
    final List<GenerationPhase> generationPhases = mySolver.solve();
    if (LOG.isDebugEnabled()) {
      dump(generationPhases);
    }
//    return phaseAsPlainList(generationPhases);
    return phaseGroupedByGenerator(generationPhases);
  }

  private static void dump(Collection<GenerationPhase> generationPhases) {
    StringBuilder sb = new StringBuilder();
    for (GenerationPhase gp : generationPhases) {
      sb.append("Phase\n");
      for (Group g : gp.getGroups()) {
        sb.append('\t');
        sb.append(g);
        sb.append('\n');
      }
    }
    LOG.debug(sb.toString());
  }

  static List<List<TemplateMappingConfiguration>> phaseAsPlainList(List<GenerationPhase> phases) {
    List<List<TemplateMappingConfiguration>> rv = new ArrayList<>();
    for (GenerationPhase gp : phases) {
      rv.add(gp.getAllElements());
    }
    return rv;
  }

  static List<List<TemplateMappingConfiguration>> phaseGroupedByGenerator(List<GenerationPhase> phases) {
    List<List<TemplateMappingConfiguration>> rv = new ArrayList<>();
    for (GenerationPhase gp : phases) {
      for (Group g : gp.groupByGenerator()) {
        rv.add(new ArrayList<>(g.getElements()));
      }
    }
    return rv;
  }

  private void loadRules() {
    // read user-defined rules
    for (TemplateModule generator : myGenerators) {
      Collection<TemplateMappingPriorityRule> priorities = generator.getPriorities();
      for (TemplateMappingPriorityRule rule : priorities) {
        processRule((MappingPriorityRule) rule, generator);
      }
    }
    // auxiliary rules to ensure generator runs no later than any generator of its target language
    MultiMap<SLanguage, TemplateModule> lang2gen = new MultiMap<>();
    for (TemplateModule generator : myGenerators) {
      LanguageRuntime sLanguage = generator.getSourceLanguage();
      SLanguage lang = sLanguage.getIdentity();
      lang2gen.putValue(lang, generator);
    }
    for (TemplateModule generator : myGenerators) {
      final RuleHelper lhsHelper = new RuleHelper(generator, new MappingConfig_RefAllLocal());
      if (lhsHelper.getAllMappings().isEmpty()) {
        continue;
      }
      HashSet<TemplateModule> targetGenerators = new HashSet<>();
      for (SLanguage targetLang : generator.getTargetLanguages()) {
        targetGenerators.addAll(lang2gen.get(targetLang));
      }
      targetGenerators.remove(generator);
      if (targetGenerators.isEmpty()) {
        continue;
      }
      if (LOG.isDebugEnabled()) {
        List<String> l = targetGenerators.stream().map(g -> g.getModuleReference().getModuleName()).collect(Collectors.toList());
        LOG.debug(String.format("Generator %s targets languages with generators %s", generator.getModuleReference().getModuleName(), l));
      }
      // for each target generator,  add a rule {all MC in the current generator} <= {all MC of target generator}, with respect to top-pri MC
      for (TemplateModule tg : targetGenerators) {
        if (myExplicitDependencies.contains(new Pair<>(generator, tg))) {
          continue;
        }
        RuleHelper rhsHelper = new RuleHelper(tg, new MappingConfig_RefAllLocal());
        if (rhsHelper.getAllMappings().isEmpty()) {
          continue;
        }
        if (rhsHelper.getTopPriMappings().isEmpty()) {
          addImplicitTargetLanguageRule(lhsHelper.getAllMappings(), generator, rhsHelper.getAllMappings(), tg);
        } else {
          if (!lhsHelper.getTopPriMappings().isEmpty()) {
            // both lhs and rhs with top pri - add two distinct rules, one for top, another for regular
            addImplicitTargetLanguageRule(lhsHelper.getTopPriMappings(), generator, rhsHelper.getTopPriMappings(), tg);
          } // otherwise don't care, establish 'not later' for regular MC only (top are going to be handled within their own top group)
          if (!lhsHelper.getRegularMappings().isEmpty() && !rhsHelper.getRegularMappings().isEmpty()) {
            addImplicitTargetLanguageRule(lhsHelper.getRegularMappings(), generator, rhsHelper.getRegularMappings(), tg);
          }
        }
      }
    }
  }

  private void addImplicitTargetLanguageRule(Collection<TemplateMappingConfiguration> lhs, TemplateModule generator1, Collection<TemplateMappingConfiguration> rhs, TemplateModule generator2) {
    MappingPriorityRule rule = new MappingPriorityRule();
    rule.setLeft(createRefs(generator1, lhs));
    rule.setRight(createRefs(generator2, rhs));
    rule.setType(RuleType.BEFORE_OR_TOGETHER);
    if (LOG.isDebugEnabled()) {
      LOG.debug(String.format("  Implicit rule added between %s and %s:", generator1.getAlias(), generator2.getAlias()));
      List<String> lhString = lhs.stream().map(TemplateMappingConfiguration::getName).collect(Collectors.toList());
      List<String> rhString = rhs.stream().map(TemplateMappingConfiguration::getName).collect(Collectors.toList());
      LOG.debug(String.format("    %s <= %s", lhString, rhString));
    }
    processRule(rule, generator1);
  }

  // XXX likely there's similar code in UI and/or TemplateUtil, need to check/refactor
  private static MappingConfig_AbstractRef createRefs(TemplateModule generator, Collection<TemplateMappingConfiguration> cfgs) {
    final MappingConfig_ExternalRef ext = new MappingConfig_ExternalRef();
    ext.setGenerator(generator.getModuleReference());
    final MappingConfig_RefSet set = new MappingConfig_RefSet();
    ext.setMappingConfig(set);
    for (TemplateMappingConfiguration mc : cfgs) {
      final MappingConfig_SimpleRef e = new MappingConfig_SimpleRef();
      e.setModelUID(mc.getMappingNode().getModelReference().toString());
      e.setNodeID(mc.getMappingNode().getNodeId().toString());
      set.getMappingConfigs().add(e);
    }
    return ext;
  }

  private void processRule(MappingPriorityRule rule, TemplateModule generator) {
    MappingConfig_AbstractRef left = rule.getLeft();
    MappingConfig_AbstractRef right = rule.getRight();
    if (left == null || right == null) return;

    final RuleHelper lhsHelper = new RuleHelper(generator, left);
    final RuleHelper rhsHelper = new RuleHelper(generator, right);
    Collection<TemplateMappingConfiguration> lhs = lhsHelper.getAllMappings();
    Collection<TemplateMappingConfiguration> rhs = rhsHelper.getAllMappings();
    if (lhs.isEmpty() || rhs.isEmpty()) {
      final String lang = generator.getSourceLanguage().getNamespace();
      if (lhs.isEmpty() && rhs.isEmpty()) {
        final String msg = String.format("Generator for language %s defines priority rule %s, both sides of the rule miss mapping configuration. The rule doesn't affect the generation and is ignored.", lang, rule);
        myConflicts.registerInvalid(generator.getModuleReference(), msg, rule);
      } else {
        final String msg = String.format("Generator for language %s defines invalid priority rule %s, with no mapping configurations specified at one side. The rule is ignored.", lang, rule);
        myConflicts.registerInvalid(generator.getModuleReference(), msg, rule);
      }
      return;
    }
    for (TemplateModule l : lhsHelper.getInvolvedGenerators()) {
      for (TemplateModule r : rhsHelper.getInvolvedGenerators()) {
        myExplicitDependencies.add(new Pair<>(l, r));
      }
    }
    switch (rule.getType()) {
      case STRICTLY_TOGETHER:
        Set<TemplateMappingConfiguration> coherentMappings = new HashSet<>(rhs);
        coherentMappings.addAll(lhs);
        mySolver.registerCoherent(coherentMappings, rule);
        return;
      case STRICTLY_BEFORE:
      case BEFORE_OR_TOGETHER:
        mySolver.establishDependency(lhs, rhs, rule);
        return;
      case AFTER_OR_TOGETHER:
      case STRICTLY_AFTER:
        mySolver.establishDependency(rhs, lhs, rule);
        return;
      default: throw new IllegalStateException(String.valueOf(rule.getType()));
    }
  }

  private class RuleHelper {
    private final TemplateModule myGenerator;
    private final MappingConfig_AbstractRef myInitialRef;
    private Collection<TemplateMappingConfiguration> myMapConfigs;

    public RuleHelper(TemplateModule generator, MappingConfig_AbstractRef mcRef) {
      myGenerator = generator;
      myInitialRef = mcRef;
    }

    public Collection<TemplateMappingConfiguration> getAllMappings() {
      build();
      return myMapConfigs;
    }

    public Collection<TemplateMappingConfiguration> getTopPriMappings() {
      ArrayList<TemplateMappingConfiguration> rv = new ArrayList<>();
      for (TemplateMappingConfiguration mc : getAllMappings()) {
        if (mc.isTopPriority()) {
          rv.add(mc);
        }
      }
      return rv;
    }

    public Collection<TemplateMappingConfiguration> getRegularMappings() {
      ArrayList<TemplateMappingConfiguration> rv = new ArrayList<>();
      for (TemplateMappingConfiguration mc : getAllMappings()) {
        if (!mc.isTopPriority()) {
          rv.add(mc);
        }
      }
      return rv;
    }

    public Collection<TemplateModule> getInvolvedGenerators() {
      HashSet<TemplateModule> rv = new HashSet<>();
      for (TemplateMappingConfiguration mc : getAllMappings()) {
        rv.add(mc.getModel().getModule());
      }
      return rv;
    }

    private void build() {
      if (myMapConfigs == null) {
        myMapConfigs = getMappingsFromRef(myInitialRef, myGenerator);
      }
    }

    private Collection<TemplateMappingConfiguration> getMappingsFromRef(MappingConfig_AbstractRef mappingRef, TemplateModule refGenerator) {
      if (mappingRef instanceof MappingConfig_RefAllGlobal) {
        return new ArrayList<>(mySolver.getKnownMapConfigs());
      }

      if (mappingRef instanceof MappingConfig_RefAllLocal) {
        List<TemplateMappingConfiguration> mappingConf = new ArrayList<>();
        for (TemplateModel templateModel : refGenerator.getModels()) {
          mappingConf.addAll(templateModel.getConfigurations());
        }
        return mappingConf;
      }

      if (mappingRef instanceof MappingConfig_RefSet) {
        List<TemplateMappingConfiguration> result = new ArrayList<>();
        MappingConfig_RefSet refSet = ((MappingConfig_RefSet) mappingRef);
        for (MappingConfig_AbstractRef simpleRef : refSet.getMappingConfigs()) {
          result.addAll(getMappingsFromRef(simpleRef, refGenerator));
        }
        return result;
      }

      if (mappingRef instanceof MappingConfig_ExternalRef) {
        SModuleReference generatorRef = ((MappingConfig_ExternalRef) mappingRef).getGenerator();
        if (generatorRef != null) {
          TemplateModule newRefGenerator = myModulesMap.get(generatorRef);
          if (newRefGenerator != null) {
            return getMappingsFromRef(((MappingConfig_ExternalRef) mappingRef).getMappingConfig(), newRefGenerator);
          } else {
            // generator is not in the plan - just ignore
            // LOG.error("couldn't get generator by uid: '" + generatorRef + "'");
          }
        }
        return Collections.emptyList();
      }

      if (mappingRef instanceof MappingConfig_SimpleRef) {
        MappingConfig_SimpleRef simpleRef = (MappingConfig_SimpleRef) mappingRef;
        String modelUID = simpleRef.getModelUID();
        String nodeID = simpleRef.getNodeID();
        if (modelUID != null && nodeID != null) {
          SModelReference reference = PersistenceFacade.getInstance().createModelReference(modelUID);
          TemplateModel refModel = myModelMap.get(reference);

          if (refModel != null) {
            if (simpleRef.includesAll()) {
              return refModel.getConfigurations();
            } else {
              SNodeReference node = new jetbrains.mps.smodel.SNodePointer(reference, PersistenceFacade.getInstance().createNodeId(nodeID));
              for (TemplateMappingConfiguration m : refModel.getConfigurations()) {
                if (node.equals(m.getMappingNode())) {
                  return Collections.singletonList(m);
                }
              }
              LOG.warn(
                  "couldn't get node by id: '" + nodeID + "' in model " + modelUID + " while loading priority rules for generator: " + myGenerator.getAlias());
            }
          } else {
            LOG.warn(
                "couldn't get model by uid: '" + modelUID + "' in generator " + refGenerator.getAlias() + " while loading priority rules for generator: " +
                    myGenerator.getAlias());
          }
        }
        return Collections.emptyList();
      }

      return Collections.emptyList();
    }
  }

  public PriorityConflicts getConflictingPriorityRules() {
    return myConflicts;
  }
}
