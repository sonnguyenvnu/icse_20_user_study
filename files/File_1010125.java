/*
 * Copyright 2003-2017 JetBrains s.r.o.
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
package jetbrains.mps.generator;

import jetbrains.mps.extapi.module.ModuleFacetBase;
import jetbrains.mps.generator.impl.GenPlanTranslator;
import jetbrains.mps.generator.impl.plan.EngagedGeneratorCollector;
import jetbrains.mps.generator.impl.plan.RegularPlanBuilder;
import jetbrains.mps.smodel.language.LanguageRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.persistence.Memento;
import org.jetbrains.mps.openapi.persistence.PersistenceFacade;

/**
 * @author Artem Tikhomirov
 * @since 3.3
 */
public class CustomGenerationModuleFacet extends ModuleFacetBase implements ModelGenerationPlan.Provider {
  public static final String FACET_TYPE = "generator";
  private SModelReference myPlanModel;
  private ModelGenerationPlan myCachedPlanInstance;
  private long myCachedPlanTimestamp;

  public CustomGenerationModuleFacet() {
    super(FACET_TYPE);
  }

  @Override
  public String getFacetPresentation() {
    return "Custom generation";
  }

  @Nullable
  public ModelGenerationPlan getPlan(@NotNull SModel model) {
    if (myPlanModel == null) {
      return null;
    }
    SModel planModel = myPlanModel.resolve(model.getRepository());
    if (planModel == null || !planModel.getRootNodes().iterator().hasNext()) {
      return null;
    }

    final long modelActualTimestamp = planModel.getSource().getTimestamp();
    if (myCachedPlanInstance != null && myCachedPlanTimestamp == modelActualTimestamp) {
      // as long as there's single plan per module, no need to create MGP instance for each model, reuse.
      return myCachedPlanInstance;
    }
    myCachedPlanTimestamp = modelActualTimestamp;

    GenPlanTranslator gpt = new GenPlanTranslator(planModel.getRootNodes().iterator().next());
    EngagedGeneratorCollector egc = new EngagedGeneratorCollector(model, null); // see comment in GenPlanExtractor regarding additional languages
    RegularPlanBuilder planBuilder = new RegularPlanBuilder(LanguageRegistry.getInstance(model.getRepository()), egc.getGenerators());
    gpt.feed(planBuilder);
    myCachedPlanInstance = planBuilder.wrapUp(gpt.getPlanIdentity());
    return myCachedPlanInstance;
  }

  // despite public, these methods are not part of the contract.
  // it's facet's implementation details

  @Nullable
  public SModelReference getPlanModelReference() {
    return myPlanModel;
  }

  public void setPlanModelReference(@Nullable SModelReference modelRef) {
    myPlanModel = modelRef;
    myCachedPlanInstance = null;
  }

  @Override
  public void load(Memento memento) {
    String value = memento.get("planModel");
    myPlanModel = value == null ? null : PersistenceFacade.getInstance().createModelReference(value);
  }

  @Override
  public void save(Memento memento) {
    memento.put("planModel", myPlanModel == null ? null : PersistenceFacade.getInstance().asString(myPlanModel));
  }
}
