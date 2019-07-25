/*
 * Copyright 2003-2016 JetBrains s.r.o.
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
package jetbrains.mps.text.impl;

import jetbrains.mps.make.java.ModelDependencies;
import jetbrains.mps.make.java.RootDependencies;
import jetbrains.mps.text.CompatibilityTextUnit;
import jetbrains.mps.text.TextGenResult;
import jetbrains.mps.text.TextUnit;
import org.jetbrains.annotations.NotNull;

/**
 * Produce collection of BaseLanguage dependencies (aka {@link ModelDependencies}) from information
 * associated with text unit. In fact, is specific to BL textgen and shall live there, however
 * there's no generic mechanism now in TextGen facet to pick all relevant data to serialize it along with
 * generated files, hence ModelDependencies in [kernel] and this builder in [textgen]
 *
 * See {@link CompatibilityTextUnit} for ideas how to get rid of explicit BL dependency.
 * Now, it seems better approach is to move this builder next to TextGen_Facet (which knows about [kernel] and [textgen]). It's still
 * not best solution (we need a mechanism to contribute these builders, along with cache generators, based on actual languages involved).
 * Shall get back to this question once RTU.findContextObject goes API/public (into TU) and I can use it without need to cast to RTU implementation
 *
 * @see jetbrains.mps.text.impl.DebugInfoBuilder
 * @author Artem Tikhomirov
 * @since 3.4
 */
public class BLDependenciesBuilder {

  @NotNull
  public ModelDependencies build(@NotNull TextGenResult textResult) {
    ModelDependencies modelDependencies = new ModelDependencies();
    for (TextUnit tu : textResult.getUnits()) {
      if (tu.getState() == TextUnit.Status.Empty) {
        continue;
      }
      if (tu instanceof RegularTextUnit) {
        RootDependencies.Source co = ((RegularTextUnit) tu).findContextObject(RootDependencies.Source.class);
        if (co != null) {
          RootDependencies deps = co.getDependencies();
          if (deps != null) {
            modelDependencies.addDependencies(deps);
          }
        }
      }
    }
    return modelDependencies;
  }
}
