package com.liferay.training.gradebook.internal.search.spi.model.index.contributor;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.HtmlParserUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;
import com.liferay.training.gradebook.model.Assignment;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Mario Cvjetojevic
 */
@Component(
        immediate = true,
        property = "indexer.class.name=com.liferay.training.gradebook.model.Assignment",
        service = ModelDocumentContributor.class
)
public class AssignmentModelDocumentContributor
        implements ModelDocumentContributor<Assignment> {

    @Override
    public void contribute(Document document, Assignment assignment) {

        // Strip HTML.

        String description = HtmlParserUtil.extractText(assignment.getDescription());
        document.addText(Field.DESCRIPTION, description);

        String title = HtmlParserUtil.extractText(assignment.getTitle());
        document.addText(Field.TITLE, title);

        document.addDate(Field.MODIFIED_DATE, assignment.getModifiedDate());

        // Handle localized fields.

        for (Locale locale : LanguageUtil.getAvailableLocales(
                assignment.getGroupId())) {

            String languageId = LocaleUtil.toLanguageId(locale);

            document.addText(
                    LocalizationUtil.getLocalizedName(
                            Field.DESCRIPTION, languageId),
                    description);
            document.addText(
                    LocalizationUtil.getLocalizedName(Field.TITLE, languageId),
                    title);
        }
    }
}