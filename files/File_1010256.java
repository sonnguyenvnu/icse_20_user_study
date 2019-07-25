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
package jetbrains.mps.errors.item;

import jetbrains.mps.errors.QuickFixProvider;
import jetbrains.mps.errors.QuickFix_Runtime;
import jetbrains.mps.errors.item.ReportItemBase.SimpleReportItemFlavour;
import jetbrains.mps.smodel.language.LanguageRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SNodeReference;
import org.jetbrains.mps.openapi.module.SRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class QuickFixRuntimeAdapter implements EditorQuickFix, NodeFlavouredItem, RuleIdFlavouredItem {
  private final LanguageRegistry myLanguageRegistry;
  private final QuickFixProvider myQuickFixProvider;
  private final SNodeReference myNode;

  public QuickFixRuntimeAdapter(@NotNull LanguageRegistry languageRegistry, @NotNull SNodeReference node, QuickFixProvider quickFixProvider) {
    myLanguageRegistry = languageRegistry;
    myQuickFixProvider = quickFixProvider;
    myNode = node;
  }

  public QuickFix_Runtime getFixRuntime() {
    return myQuickFixProvider.getQuickFix(myLanguageRegistry);
  }

  @Override
  public boolean isExecutedImmediately() {
    return myQuickFixProvider.isExecutedImmediately();
  }

  @Override
  public void execute(SRepository repository) {
    getFixRuntime().execute(myNode.resolve(repository));
  }

  @Override
  public String getDescription(SRepository repository) {
    return getFixRuntime().getDescription(myNode.resolve(repository));
  }

  @NotNull
  @Override
  public SNodeReference getNode() {
    return myNode;
  }

  public static final SimpleReportItemFlavour<QuickFixRuntimeAdapter, QuickFix_Runtime> FLAVOUR_QUICKFIX_RUNTIME =
      new SimpleReportItemFlavour<>("FLAVOUR_QUICKFIX_RUNTIME", QuickFixRuntimeAdapter.class, QuickFixRuntimeAdapter::getFixRuntime);

  @Override
  public Collection<TypesystemRuleId> getRuleId() {
    SNodeReference declarationNode = getFixRuntime().getDeclarationNode();
    if (declarationNode != null) {
      return Collections.singleton(new TypesystemRuleId(declarationNode));
    } else {
      return Collections.emptyList();
    }
  }

  @Override
  public Set<ReportItemFlavour<?, ?>> getIdFlavours() {
    return new HashSet<>(Arrays.asList(FLAVOUR_CLASS, FLAVOUR_RULE_ID, FLAVOUR_NODE));
  }

  @Override
  public boolean isAlive(SRepository repository) {
    return getNode().resolve(repository) != null;
  }
}
