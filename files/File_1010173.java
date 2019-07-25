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
import jetbrains.mps.generator.ModelGenerationPlan.Checkpoint;
import jetbrains.mps.generator.ModelGenerationPlan.Step;
import jetbrains.mps.generator.ModelGenerationPlan.Transform;
import jetbrains.mps.generator.RigidGenerationPlan;
import jetbrains.mps.generator.plan.CheckpointIdentity;
import jetbrains.mps.generator.plan.PlanIdentity;
import jetbrains.mps.generator.runtime.TemplateMappingConfiguration;
import jetbrains.mps.generator.runtime.TemplateModel;
import jetbrains.mps.generator.runtime.TemplateModule;
import jetbrains.mps.smodel.Generator;
import jetbrains.mps.smodel.language.GeneratorRuntime;
import jetbrains.mps.smodel.language.LanguageRegistry;
import jetbrains.mps.smodel.language.LanguageRuntime;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.language.SLanguage;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Supports limited set of GP statements. {@link #wrapUp(PlanIdentity)} captures present state and any subsequent statements augment what's there already.
 * Perhaps, shall bear a name that reflects its limitation, (Basic/Bare?)
 *
 * @author Artem Tikhomirov
 * @since 2017.1
 */
public class RigidPlanBuilder implements GenerationPlanBuilder {
  private final LanguageRegistry myLanguageRegistry;
  private final List<Step> mySteps = new ArrayList<>();

  public RigidPlanBuilder(@NotNull LanguageRegistry languageRegistry) {
    myLanguageRegistry = languageRegistry;
  }

  @Override
  public void transformLanguage(@NotNull SLanguage ... languages) {
    ArrayList<TemplateMappingConfiguration> mc = new ArrayList<>();
    for (SLanguage language : languages) {
      if (language == null) {
        continue; // FIXME throw an RT exception
      }
      LanguageRuntime lr = myLanguageRegistry.getLanguage(language);
      if (lr == null) {
        continue; // FIXME throw an RT exception
      }
      for (GeneratorRuntime gr : lr.getGenerators()) {
        fillMC(gr, mc);
      }
    }
    mySteps.add(new Transform(mc));
  }

  @Override
  public void applyGenerator(@NotNull SModule ... generators) {
    ArrayList<TemplateMappingConfiguration> mc = new ArrayList<>();
    for (SModule generator : generators) {
      if (!(generator instanceof Generator)) {
        continue; // FIXME throw an RT exception
      }
      GeneratorRuntime gr = myLanguageRegistry.getGenerator(generator.getModuleReference());
      if (gr == null) {
        continue; // FIXME throw an RT exception
      }
      fillMC(gr, mc);
    }
    mySteps.add(new Transform(mc));
  }

  @Override
  public void applyGeneratorWithExtended(@NotNull SModule ... generator) {
    throw new UnsupportedOperationException("This implementation of plan builder doesn't support requested functionality");
  }

  @Override
  public void applyGenerators(@NotNull Collection<SModuleReference> generators, @NotNull BuilderOption... options) {
    throw new UnsupportedOperationException("This implementation of plan builder doesn't support requested functionality");
  }

  @Override
  public void apply(@NotNull Collection<TemplateMappingConfiguration> tmc) {
    mySteps.add(new Transform(tmc));
  }

  @Override
  public void recordCheckpoint(@NotNull CheckpointIdentity cp) {
    // we shall respect plan of the cp, do not assume it's from the plan being constructed
    mySteps.add(new Checkpoint(cp, false));
  }

  @Override
  public void synchronizeWithCheckpoint(@NotNull CheckpointIdentity cp) {
    mySteps.add(new Checkpoint(cp, true));
  }

  /**
   * XXX I don't really need RigidGenerationPlan return value here (moreover, RGP shall move into impl package),
   *     just highlight the fact clients can utilize their knowledge about factory instance.
   */
  @NotNull
  @Override
  public RigidGenerationPlan wrapUp(@NotNull PlanIdentity planIdentity) {
    return new RigidGenerationPlan(planIdentity, mySteps);
  }

  @Override
  public GenerationPlanBuilder fork() {
    throw new UnsupportedOperationException();
  }

  private static void fillMC(GeneratorRuntime gr, List<TemplateMappingConfiguration> mc) {
    if (!(gr instanceof TemplateModule)) {
      return;
    }
    for (TemplateModel tm : ((TemplateModule) gr).getModels()) {
      mc.addAll(tm.getConfigurations());
    }
  }
}
