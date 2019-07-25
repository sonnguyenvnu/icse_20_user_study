/*
 * Copyright 2003-2019 JetBrains s.r.o.
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
package jetbrains.mps.nodeEditor.cellMenu;

import jetbrains.mps.editor.runtime.completion.CompletionItemInformation;
import jetbrains.mps.editor.runtime.completion.CompletionMenuItemCustomizationContext;
import jetbrains.mps.editor.runtime.menus.EditorMenuItemCompositeCustomizationContext;
import jetbrains.mps.editor.runtime.menus.EditorMenuItemModifyingCustomizationContext;
import jetbrains.mps.nodeEditor.EditorSettings;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.openapi.editor.cells.EditorCellContext;
import jetbrains.mps.openapi.editor.cells.SubstituteAction;
import jetbrains.mps.openapi.editor.descriptor.EditorAspectDescriptor;
import jetbrains.mps.openapi.editor.menus.style.EditorMenuItemCustomizer;
import jetbrains.mps.openapi.editor.menus.transformation.SNodeLocation;
import jetbrains.mps.openapi.editor.menus.transformation.SNodeLocation.FromNode;
import jetbrains.mps.openapi.editor.menus.transformation.SPropertyInfo;
import jetbrains.mps.smodel.adapter.MetaAdapterByDeclaration;
import jetbrains.mps.smodel.language.LanguageRegistry;
import jetbrains.mps.smodel.presentation.NodePresentationUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.language.SProperty;
import org.jetbrains.mps.openapi.language.SReferenceLink;
import org.jetbrains.mps.openapi.model.SNode;

import java.awt.Color;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

class CompletionCustomizationManager {
  private static final Logger LOG = LogManager.getLogger(CompletionCustomizationManager.class);
  public static final EditorMenuItemStyleImpl EMPTY_STYLE = new EditorMenuItemStyleImpl();

  private Map<String, Map<SubstituteAction, EditorMenuItemStyleImpl>> myPatternAndActionToCustomization = new HashMap<>();
  private Map<String, Map<SubstituteAction, String>> myPatternAndActionToVisibleMatchingText = new HashMap<>();
  private Map<String, Map<SubstituteAction, Double>> myPatternAndActionToPriority = new HashMap<>();
  private Map<SubstituteAction, Object> myActionToParameterObject = new HashMap<>();
  @Nullable
  private SNodeLocation myNodeLocation;
  @Nullable
  private SReferenceLink myReferenceLink;
  @Nullable
  private SProperty myProperty;
  private final Set<EditorMenuItemCustomizer> myCustomizers;

  private boolean myShouldApplyCustomStyle;


  CompletionCustomizationManager(@NotNull EditorCell contextCell) {
    myShouldApplyCustomStyle = EditorSettings.getInstance().isCompletionStyling();
    myCustomizers = new HashSet<>();
    if (myShouldApplyCustomStyle) {
      initContext(contextCell);
      LanguageRegistry.getInstance(contextCell.getEditorComponent().getEditorContext().getRepository()).withAvailableLanguages(languageRuntime -> {
        EditorAspectDescriptor aspect = languageRuntime.getAspect(EditorAspectDescriptor.class);
        if (aspect != null) {
          myCustomizers.addAll(aspect.getEditorMenuItemCustomizers());
        }
      });
    }
  }


  private void initContext(@NotNull EditorCell contextCell) {
    EditorCell currentCell = contextCell;
    do {
      if (currentCell.getSRole() instanceof SReferenceLink) {
        myNodeLocation = new SNodeLocation.FromNode(currentCell.getSNode());
        myReferenceLink = ((SReferenceLink) currentCell.getSRole());
        return;
      } else {
        EditorCellContext cellContext = currentCell.getCellContext();
        if (cellContext != null) {
          SPropertyInfo propertyInfo = cellContext.getPropertyInfo();
          if (propertyInfo != null) {
            myNodeLocation = new SNodeLocation.FromNode(currentCell.getSNode());
            myProperty = propertyInfo.getProperty();
            return;
          }
          if (cellContext.getNodeLocation() != null) {
            myNodeLocation = cellContext.getNodeLocation();
            return;
          }
        }
      }
      if (currentCell.isBig()) {
        myNodeLocation = new FromNode(contextCell.getSNode());
        return;
      }
      currentCell = currentCell.getParent();
    } while (currentCell != null);
  }

  boolean isBold(SubstituteAction action, String pattern) {
    return getActionStyle(action, pattern, EditorMenuItemStyleImpl::isBold);
  }

  boolean isItalic(SubstituteAction action, String pattern) {
    return getActionStyle(action, pattern, EditorMenuItemStyleImpl::isItalic);
  }

  boolean isStrikeout(SubstituteAction action, String pattern) {
    return getActionStyle(action, pattern, EditorMenuItemStyleImpl::isStrikeout);
  }

  boolean getVisibility(SubstituteAction action, String pattern) {
    return getActionStyle(action, pattern, EditorMenuItemStyleImpl::isVisible);
  }

  Optional<Color> getBackgroundColor(SubstituteAction action, String pattern) {
    return getActionStyle(action, pattern, EditorMenuItemStyleImpl::getBackgroundColor);
  }

  Optional<Color> getTextColor(SubstituteAction action, String pattern) {
    return getActionStyle(action, pattern, EditorMenuItemStyleImpl::getTextColor);
  }

  Optional<String> getDescriptionText(SubstituteAction action, String pattern) {
    return getActionStyle(action, pattern, EditorMenuItemStyleImpl::getDescriptionText);
  }


  private double getLocalSortPriority(SubstituteAction action) {
    final Object parameterObject = myActionToParameterObject.computeIfAbsent(action, SubstituteAction::getParameterObject);
    if (parameterObject instanceof SNode) {
      return NodePresentationUtil.getSortPriority(action.getSourceNode(), (SNode) parameterObject);
    } else {
      return 0d;
    }
  }

  private double getPriority(SubstituteAction action, String pattern) {
    return myPatternAndActionToPriority.computeIfAbsent(pattern, s -> new HashMap<>())
                                       .computeIfAbsent(action, theAction -> getActionStyle(action, pattern, EditorMenuItemStyleImpl::getPriority));
  }

  private String getVisibleMatchingText(SubstituteAction action, String pattern) {
    return myPatternAndActionToVisibleMatchingText.computeIfAbsent(pattern, s -> new HashMap<>())
                                                  .computeIfAbsent(action, theAction -> theAction.getVisibleMatchingText(pattern));
  }

  private <T> T getActionStyle(SubstituteAction action, String pattern, Function<EditorMenuItemStyleImpl, T> styleCalculator) {
    return styleCalculator.apply(getStyle(action, pattern));
  }

  @NotNull
  private EditorMenuItemStyleImpl getStyle(SubstituteAction action, String pattern) {
    if (!myShouldApplyCustomStyle) {
      return EMPTY_STYLE;
    }
    Map<SubstituteAction, EditorMenuItemStyleImpl> actionsToStyle =
        myPatternAndActionToCustomization.computeIfAbsent(pattern, s -> new HashMap<>());
    EditorMenuItemStyleImpl completionItemStyle = actionsToStyle.get(action);
    if (completionItemStyle == null) {
      completionItemStyle = new EditorMenuItemStyleImpl();
      try {
        action.customize(pattern, completionItemStyle);
      } catch (Throwable t) {
        LOG.error("Error while executing the customization", t);
        actionsToStyle.put(action, EMPTY_STYLE);
        return EMPTY_STYLE;
      }

      //default customization if action did not implement the customization or did not match any stylers
      if (!completionItemStyle.wasCustomized() && myNodeLocation != null) {
        doDefaultCustomization(action, pattern, completionItemStyle);
      }
      actionsToStyle.put(action, completionItemStyle);
    }
    return completionItemStyle;
  }

  private void doDefaultCustomization(SubstituteAction action, String pattern, EditorMenuItemStyleImpl completionItemStyle) {
    CompletionItemInformation itemInformation = getItemInformation(action, pattern);
    if (itemInformation != null) {
      assert myNodeLocation != null;
      EditorMenuItemModifyingCustomizationContext contextNodeCustomizationContext =
          new EditorMenuItemModifyingCustomizationContext(myNodeLocation.getContextNode(), null, myProperty,
                                                          myReferenceLink);
      EditorMenuItemModifyingCustomizationContext parentNodeCustomizationContext =
          new EditorMenuItemModifyingCustomizationContext(myNodeLocation.getParent(), myNodeLocation.getContainmentLink(), null, null);
      customizeWithModificationContext(contextNodeCustomizationContext, itemInformation, completionItemStyle);
      customizeWithModificationContext(parentNodeCustomizationContext, itemInformation, completionItemStyle);
    }
  }

  private void customizeWithModificationContext(@NotNull EditorMenuItemModifyingCustomizationContext modifyingContext,
                                                @NotNull CompletionItemInformation itemInformation, EditorMenuItemStyleImpl completionItemStyle) {
    EditorMenuItemCompositeCustomizationContext context =
        new EditorMenuItemCompositeCustomizationContext(modifyingContext, new CompletionMenuItemCustomizationContext(itemInformation));
    for (EditorMenuItemCustomizer customizer : myCustomizers) {
      customizer.customize(completionItemStyle, context);
    }
  }

  @Nullable
  private CompletionItemInformation getItemInformation(SubstituteAction action, String pattern) {
    try {
      SNode outputConceptNode = action.getOutputConcept();
      SAbstractConcept outputConcept = outputConceptNode != null ? MetaAdapterByDeclaration.getConcept(outputConceptNode) : null;
      return new CompletionItemInformation(action.getParameterObject(), outputConcept,
                                           action.getMatchingText(pattern), action.getDescriptionText(pattern));
    } catch (Throwable t) {
      LOG.error(t, t);
    }
    return null;
  }

  public void sort(List<SubstituteAction> substituteActions, String pattern) {
    Comparator<SubstituteAction> comparator = (action1, action2) -> {
      if (action1 == action2) {
        return 0;
      }
      double priority1 = getPriority(action1, pattern);
      double priority2 = getPriority(action2, pattern);
      int compare = Double.compare(priority2, priority1);
      if (compare != 0) {
        return compare;
      }
      priority1 = getLocalSortPriority(action1);
      priority2 = getLocalSortPriority(action2);
      compare = Double.compare(priority1, priority2);
      if (compare != 0) {
        return compare;
      }

      String visibleMatchingText1 = getVisibleMatchingText(action1, pattern);
      String visibleMatchingText2 = getVisibleMatchingText(action2, pattern);
      boolean null_s1 = (visibleMatchingText1 == null || visibleMatchingText1.length() == 0);
      boolean null_s2 = (visibleMatchingText2 == null || visibleMatchingText2.length() == 0);
      if (null_s1) {
        return 1;
      }
      if (null_s2) {
        return -1;
      }
      return visibleMatchingText1.compareTo(visibleMatchingText2);
    };
    try {
      substituteActions.sort(comparator);
    } catch (Throwable t) {
      LOG.error(t, t);
    }
  }


}
