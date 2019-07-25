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

import jetbrains.mps.generator.GenerationPlanBuilder;
import jetbrains.mps.generator.ModelGenerationPlan;
import jetbrains.mps.generator.ModelGenerationPlan.Checkpoint;
import jetbrains.mps.generator.ModelGenerationPlan.Fork;
import jetbrains.mps.generator.ModelGenerationPlan.Step;
import jetbrains.mps.generator.ModelGenerationPlan.Transform;
import jetbrains.mps.generator.RigidGenerationPlan;
import jetbrains.mps.generator.plan.CheckpointIdentity;
import jetbrains.mps.generator.plan.PlanIdentity;
import jetbrains.mps.generator.runtime.TemplateMappingConfiguration;
import jetbrains.mps.generator.runtime.TemplateModel;
import jetbrains.mps.generator.runtime.TemplateModule;
import jetbrains.mps.messages.IMessageHandler;
import jetbrains.mps.messages.LogHandler;
import jetbrains.mps.messages.Message;
import jetbrains.mps.messages.MessageKind;
import jetbrains.mps.smodel.language.GeneratorRuntime;
import jetbrains.mps.smodel.language.LanguageRegistry;
import jetbrains.mps.smodel.language.LanguageRuntime;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SLanguage;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * XXX Now, I don't like the idea both plan builder and its client (GenPlanTranslator) need runtime information about deployed modules. I shall restrict
 *  API of this class to identity of generators/languages/MCs. Then, both present interpreted and future generated plan translation code could be simple and
 *  straightforward, without a need to figure out particular TemplateModule from a runtime registry it hardly has access to.
 *
 * XXX myEngagedGenerators are in use only during wrapUp. Perhaps, shall pass them at this moment, rather than at construction time?
 *
 * Though I hate the name as it doesn't tell anything, it's a plan builder for regular use, targeted
 * for everyday scenarios like model make in IDE. Name alternatives are ScopedPlanBuilder, RestrictExtendsPB and others of the same degree of imperfection.
 * Likely, we'll need different approaches to extension processing, and the name shall reflect the approach, but at the moment I can't come up with a better one.
 * <p/>
 * Supports all operations.
 * <p/>
 * IMPORTANT: Treats extensions to generators on a 'first come, first served basis'.
 * Tries to consume as many extensions as possible for the first statement that calls for extensions.
 * Extensions are looked up among generators denoted as 'engaged'. If generatorB extends generatorA, and there's 'apply with extensions generatorA',
 * then generatorB would get into final transformation sequence only when it's listed among 'engaged' generators supplied at the builder's construction time.
 * Note, this implementation use these generators to limit 'upper' boundary only, in the aforementioned example it doesn't matter whether list of 'engaged'
 * includes generatorA, which gets into transformation sequence regardless of 'engaged' generators. Perhaps, this has to be changed (and ScopedPB would be
 * more appropriate name then)
 *
 * @author Artem Tikhomirov
 * @since 2017.1
 */
public class RegularPlanBuilder implements GenerationPlanBuilder {
  private final LanguageRegistry myLanguageRegistry;
  private final Collection<TemplateModule> myEngagedGenerators;
  private final IMessageHandler myMessageHandler;
  private final List<StepEntry> mySteps = new ArrayList<>();

  public RegularPlanBuilder(@NotNull LanguageRegistry languageRegistry, Collection<TemplateModule> allEngagedGenerators) {
    this(languageRegistry, allEngagedGenerators, new LogHandler(Logger.getLogger(RegularPlanBuilder.class)));
  }

  public RegularPlanBuilder(@NotNull LanguageRegistry languageRegistry, Collection<TemplateModule> allEngagedGenerators, @Nullable IMessageHandler messageHandler) {
    myLanguageRegistry = languageRegistry;
    myEngagedGenerators = allEngagedGenerators;
    myMessageHandler = messageHandler == null ? IMessageHandler.NULL_HANDLER : messageHandler;
  }

  @Override
  public void transformLanguage(@NotNull SLanguage... languages) {
    ArrayList<TemplateModule> tm = new ArrayList<>(languages.length);
    for (SLanguage language : languages) {
      if (language == null) {
        myMessageHandler.handle(new Message(MessageKind.ERROR, RegularPlanBuilder.class, "Request to apply null language"));
        continue;
      }
      LanguageRuntime lr = myLanguageRegistry.getLanguage(language);
      if (lr == null) {
        myMessageHandler.handle(new Message(MessageKind.ERROR, RegularPlanBuilder.class, String.format("Language %s not found among deployed", language)));
        continue;
      }
      lr.getGenerators().stream().filter(TemplateModule.class::isInstance).map(TemplateModule.class::cast).forEach(tm::add);
    }
    // Perhaps, shall record LanguageEntry and build set of templates when required?
    mySteps.add(new TransformEntry(this, tm, true, false));
  }

  @Override
  public void applyGenerator(@NotNull SModule... generators) {
    mySteps.add(new TransformEntry(this, asTemplateModules(generators), true, false));
  }

  @Override
  public void applyGeneratorWithExtended(@NotNull SModule ... generator) {
    mySteps.add(new TransformEntry(this, asTemplateModules(generator), false, false));
  }

  @Override
  public void applyGenerators(@NotNull Collection<SModuleReference> generators, @NotNull BuilderOption... options) {
    boolean withExtended = BuilderOption.WithExtendedGenerators.presentIn(options);
    boolean respectPriorityRules = withExtended && BuilderOption.WithPriorityRules.presentIn(options);
    mySteps.add(new TransformEntry(this, asTemplateModules(generators), !withExtended, respectPriorityRules));
  }

  @Override
  public void apply(@NotNull Collection<TemplateMappingConfiguration> tmc) {
    mySteps.add(new PreparedEntry(new ArrayList<>(tmc)));
  }

  @Override
  public void recordCheckpoint(@NotNull CheckpointIdentity cp) {
    mySteps.add(new CheckpointEntry(cp, false));
  }

  @Override
  public void synchronizeWithCheckpoint(@NotNull CheckpointIdentity cp) {
    mySteps.add(new CheckpointEntry(cp, true));
  }

  @NotNull
  @Override
  public ModelGenerationPlan wrapUp(@NotNull PlanIdentity planIdentity) {
    HashSet<TemplateModule> explicitlyMentioned = new HashSet<>();
    mySteps.forEach(s -> s.reportInvolvedGenerators(explicitlyMentioned));
    HashSet<TemplateModule> availableAsExt = new HashSet<>(myEngagedGenerators);
    // FIXME quite ineffective way to deal with LanguageRuntime.getGenerators producing new instance of TemplateModule each time asked.
    // XXX with no interpreted generators instantiated from LR.getGenerators, can get rid of this code.
    availableAsExt.removeIf(tm -> explicitlyMentioned.stream().anyMatch(m -> m.getModuleReference().equals(tm.getModuleReference())));
    class S {
      public final TemplateModule generator;
      public final Collection<SModuleReference> directlyExtendedGenerators;
      public S(TemplateModule g) {
        generator = g;
        // XXX use SModuleReference here as a workaround to deal with !TemplateModuleX.equals(TemplateModuleX) if obtained with distinct calls to LR.getGenerators()
        directlyExtendedGenerators = g.getExtendedGenerators().stream().map(TemplateModule::getModuleReference).collect(Collectors.toList());
      }
    }
    S[] topoOrder = new S[availableAsExt.size()]; // it's partial topo ordering, just for extended generators mentioned directly
    int i = 0;
    for (TemplateModule extCandidate : availableAsExt) {
      topoOrder[i++] = new S(extCandidate);
    }
    // FIXME Comparator violates transitive constraint: given C -> B -> A, it answers A < B and B < C, but tells A == C
    //       see https://youtrack.jetbrains.com/issue/MPS-27394
    Arrays.sort(topoOrder, (o1, o2) -> {
      // o2 needs o1, then o1 < o2
      if (o2.directlyExtendedGenerators.contains(o1.generator.getModuleReference())) {
        return -1;
      }
      // o1 needs o2, then o1 > o2
      if (o1.directlyExtendedGenerators.contains(o2.generator.getModuleReference())) {
        return 1;
      }
      return 0;
    });
    // It's intentional (though not necessarily right) that we look into generators extended directly only, not transitive closure.
    // The idea is that given C extends B extends A, and A.withExtensions and C among availableExt and no B whatsoever, I don't want C to show up.
    //
    /* C -> B -> A
     * D -> A
     * E -> B & D
     * G -> E
     * F -> E & A
     * Extends direction is bottom to top:
     * A__
     * |\ \
     * | \ \
     * B  D \
     * |\ |  \
     * | \|  |
     * C  E  |
     *    |\ |
     *    | \|
     *    G  F
     * If A and B explicitly mentioned in a plan:
     * For B: C, E, G, F
     * For A: D, E, G, F
     * If A, B and E explicitly mentioned in a plan:
     * For A: D, F
     * For B: C
     * For E: G, F
     */
    for (S s : topoOrder) {
      for (StepEntry se : mySteps) {
        se.registerIfIntersects(s.directlyExtendedGenerators, s.generator);
      }
    }
    ArrayList<Step> steps = new ArrayList<>(mySteps.size());
    mySteps.forEach(s -> s.createStep(steps));
    return new RigidGenerationPlan(planIdentity, steps);
  }

  @Override
  public GenerationPlanBuilder fork() {
    final ForkEntry forkStep = new ForkEntry();
    mySteps.add(forkStep);
    return new RegularPlanBuilder(myLanguageRegistry, myEngagedGenerators, myMessageHandler) {
      @NotNull
      @Override
      public ModelGenerationPlan wrapUp(@NotNull PlanIdentity planIdentity) {
        forkStep.steps(getEntries());
        // blank, non-null return value, shall be ignored
        return new RigidGenerationPlan(planIdentity, Collections.emptyList());
      }
    };
  }

  // just to ensure subclass in fork() accesses right mySteps field (not the one from enclosing entry)
  protected final List<StepEntry> getEntries() {
    return mySteps;
  }

  private Collection<TemplateModule> asTemplateModules(@NotNull Collection<SModuleReference> generators) {
    ArrayList<TemplateModule> tm = new ArrayList<>(generators.size());
    for (SModuleReference generatorIdentity : generators) {
      TemplateModule gr = findDeployedGenerator(generatorIdentity);
      if (gr != null) {
        tm.add(gr);
      }
    }
    return tm;
  }

  private Collection<TemplateModule> asTemplateModules(@NotNull SModule... generators) {
    ArrayList<TemplateModule> tm = new ArrayList<>(generators.length);
    for (SModule generator : generators) {
      if (generator == null) {
        myMessageHandler.handle(new Message(MessageKind.ERROR, RegularPlanBuilder.class, "Request to transform with null generator"));
        continue;
      }
      TemplateModule gr = findDeployedGenerator(generator.getModuleReference());
      if (gr != null) {
        tm.add(gr);
      }
    }
    return tm;
  }

  @Nullable
  private TemplateModule findDeployedGenerator(SModuleReference deployedGeneratorIdentity) {
    GeneratorRuntime gr = myLanguageRegistry.getGenerator(deployedGeneratorIdentity);
    if (gr instanceof TemplateModule) {
      return ((TemplateModule) gr);
    }
    String msg = String.format(gr == null ? "Generator %s not found among deployed" : "Generator %s is not a TemplateModule", deployedGeneratorIdentity.getModuleName());
    myMessageHandler.handle(new Message(MessageKind.ERROR, RegularPlanBuilder.class, msg).setHintObject(deployedGeneratorIdentity));
    return null;
  }

  private boolean isNextStepIsPersistedCheckpoint(TransformEntry entry) {
    int i = mySteps.indexOf(entry);
    assert i >= 0; // no one else could pass here anything unexpected
    return mySteps.size() > i + 1 && mySteps.get(i+1) instanceof CheckpointEntry;
  }


  private interface StepEntry {
    /**
     * @param result collections to feed with generators of this step
     */
    void reportInvolvedGenerators(Collection<TemplateModule> result);

    /**
     * Step has a chance to 'consume' {@code extCandidate} generator if the step explicitly lists any of {@code directExtendedGenerators} as engaged.
     * 'Consumed' here doesn't mean other steps could not consume it as well. Basically, its PlanBulder telling its step entries: "look, here's a generator
     * I'd like to put somewhere, grab it if you like".
     * @param directExtendedGenerators generators directly extended by {@code extCandidate}, just an handy, calculated-once set.
     * @param extCandidate generator
     */
    void registerIfIntersects(Collection<SModuleReference> directExtendedGenerators, TemplateModule extCandidate);

    /**
     * @param steps ordered collection to receive new plan step(s) according to this entry.
     */
    void createStep(List<Step> steps);
  }

  private static class TransformEntry implements StepEntry {
    private final RegularPlanBuilder myPlanBuilder;
    private final ArrayList<TemplateModule> myGenerators;
    private final boolean myIsSealed; // true if no extensions are considered.
    // true if shall read priority rules from specified generators and break this step further down to smaller according to these rules.
    private final boolean myRespectPriorityRules;
    private final ArrayList<TemplateModule> myExtensions = new ArrayList<>(4);

    TransformEntry(RegularPlanBuilder planBuilder, Collection<TemplateModule> generators, boolean isSealed, boolean respectPriorityRules) {
      myPlanBuilder = planBuilder;
      myGenerators = new ArrayList<>(generators);
      myIsSealed = isSealed;
      myRespectPriorityRules = respectPriorityRules;
    }

    @Override
    public void reportInvolvedGenerators(Collection<TemplateModule> result) {
      result.addAll(myGenerators);
    }

    @Override
    public void registerIfIntersects(Collection<SModuleReference> directExtendedGenerators, TemplateModule extCandidate) {
      if (myIsSealed) {
        return;
      }
      if (myExtensions.contains(extCandidate)) {
        // already seen that one
        return;
      }

      if (Stream.concat(myGenerators.stream(), myExtensions.stream()).map(TemplateModule::getModuleReference).anyMatch(directExtendedGenerators::contains)) {
        myExtensions.add(extCandidate);
      }
    }

    @Override
    public void createStep(List<Step> steps) {
      Stream<TemplateModule> generators = Stream.concat(myGenerators.stream(), myExtensions.stream());
      if (!myIsSealed && myRespectPriorityRules) {
        final boolean isBeforeCheckpoint = myPlanBuilder.isNextStepIsPersistedCheckpoint(this);
        GenerationPartitioner gp = new GenerationPartitioner(generators.collect(Collectors.toList()));
        List<List<TemplateMappingConfiguration>> mappingSets = gp.createMappingSets();
        // if there's only 1 step, there's no need to collect its labels for propagation, they are already there in the last (and only) set of MLs.
        final boolean needToPropagateLabeledTransforms = isBeforeCheckpoint && mappingSets.size() > 1;
        for (List<TemplateMappingConfiguration> tmc4Step : mappingSets) {
          steps.add(new Transform(tmc4Step, needToPropagateLabeledTransforms));
        }
      } else {
        ArrayList<TemplateMappingConfiguration> tmc = new ArrayList<>();
        generators.flatMap(tm -> tm.getModels().stream()).map(TemplateModel::getConfigurations).forEach(tmc::addAll);
        steps.add(new Transform(tmc));
      }
    }
  }


  private static class CheckpointEntry implements StepEntry {
    private final CheckpointIdentity myIdentity;
    private final boolean myIsSynchOnly;

    CheckpointEntry(CheckpointIdentity cpIdentity, boolean isSynchOnly) {
      myIdentity = cpIdentity;
      myIsSynchOnly = isSynchOnly;
    }

    @Override
    public void reportInvolvedGenerators(Collection<TemplateModule> result) {
      // no-op
    }


    @Override
    public void registerIfIntersects(Collection<SModuleReference> directExtendedGenerators, TemplateModule extCandidate) {
      // no-op
    }

    @Override
    public void createStep(List<Step> steps) {
      steps.add(new Checkpoint(myIdentity, myIsSynchOnly));
    }
  }

  private static class PreparedEntry implements StepEntry {
    private final List<TemplateMappingConfiguration> myElements;

    PreparedEntry(List<TemplateMappingConfiguration> tmc) {
      myElements = tmc;
    }

    @Override
    public void reportInvolvedGenerators(Collection<TemplateModule> result) {
      // Report tmc's module as 'involved', effectively telling that
      // generators of explicitly specified TMCs are NOT available for consideration with
      // 'generators with extensions' stmt. We treat explicitly specified MC as 'LD knows what to do with a generator'
      for (TemplateMappingConfiguration tmc : myElements) {
        result.add(tmc.getModel().getModule());
      }
    }

    @Override
    public void registerIfIntersects(Collection<SModuleReference> directExtendedGenerators, TemplateModule extCandidate) {
      // no-op
    }

    @Override
    public void createStep(List<Step> steps) {
      steps.add(new Transform(myElements));
    }
  }

  private static class ForkEntry implements StepEntry {
    private List<StepEntry> mySteps = Collections.emptyList();

    public void steps(List<StepEntry> steps) {
      assert !steps.contains(this) : "Fork step shall not include itself";
      mySteps = steps;
    }

    @Override
    public void reportInvolvedGenerators(Collection<TemplateModule> result) {
      mySteps.forEach(s -> s.reportInvolvedGenerators(result));
    }

    @Override
    public void registerIfIntersects(Collection<SModuleReference> directExtendedGenerators, TemplateModule extCandidate) {
      mySteps.forEach(s -> s.registerIfIntersects(directExtendedGenerators, extCandidate));
    }

    @Override
    public void createStep(List<Step> steps) {
      final ArrayList<Step> branch = new ArrayList<>();
      mySteps.forEach(s -> s.createStep(branch));
      steps.add(new Fork(branch));
    }
  }
}
