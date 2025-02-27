/* Licensed under EPL-2.0 2022. */
package edu.kit.kastel.eclipse.common.view.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import edu.kit.kastel.eclipse.common.api.PreferenceConstants;
import edu.kit.kastel.eclipse.common.view.activator.CommonActivator;
import edu.kit.kastel.eclipse.common.view.languages.LanguageSettings;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = CommonActivator.getDefault().getPreferenceStore();
		store.setDefault(PreferenceConstants.GENERAL_ARTEMIS_URL, "");
		store.setDefault(PreferenceConstants.GENERAL_ADVANCED_ARTEMIS_USER, "");
		store.setDefault(PreferenceConstants.GENERAL_ADVANCED_ARTEMIS_PASSWORD, "");
		store.setDefault(PreferenceConstants.GENERAL_ADVANCED_GIT_TOKEN, "");

		store.setDefault(PreferenceConstants.GRADING_ABSOLUTE_CONFIG_PATH, "");
		store.setDefault(PreferenceConstants.GRADING_VIEW_BUTTONS_IN_COLUMN, 3);
		store.setDefault(PreferenceConstants.GRADING_VIEW_PREFERS_LARGE_PENALTY_TEXT_PATH, false);
		store.setDefault(PreferenceConstants.GRADING_VIEW_PREFERS_TEXT_WRAPPING_IN_PENALTY_TEXT_PATH, false);
		store.setDefault(PreferenceConstants.GENERAL_OVERRIDE_DEFAULT_PREFERENCES, true);
		store.setDefault(PreferenceConstants.GENERAL_PREFERRED_LANGUAGE, LanguageSettings.getDefaultLanguage().languageDisplayName());
		store.setDefault(PreferenceConstants.SEARCH_IN_MISTAKE_MESSAGES, true);
		store.setDefault(PreferenceConstants.OPEN_FILES_ON_ASSESSMENT_START, PreferenceConstants.OPEN_FILES_ON_ASSESSMENT_START_MAIN);
	}

}
