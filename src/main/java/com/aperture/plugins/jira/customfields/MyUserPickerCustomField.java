package com.aperture.plugins.jira.customfields;

import com.atlassian.jira.issue.customfields.impl.UserCFType;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;
import com.atlassian.jira.issue.customfields.impl.FieldValidationException;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.customfields.CustomFieldType;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.plugin.spring.scanner.annotation.imports.JiraImport;
import java.util.Map;

public class MyUserPickerCustomField extends UserCFType {
    public MyUserPickerCustomField(
            @JiraImport CustomFieldValuePersister customFieldValuePersister,
            @JiraImport GenericConfigManager genericConfigManager,
            @JiraImport JiraAuthenticationContext jiraAuthenticationContext
    ) {
        super(customFieldValuePersister, genericConfigManager, jiraAuthenticationContext);
    }

    public Map<String, Object> getVelocityParameters(
            Issue issue,
            CustomField field,
            FieldLayoutItem fieldLayoutItem
    ) {
        Map<String, Object> velocityParams = super.getVelocityParameters(issue, field, fieldLayoutItem);
        // custom velocity parameters goes here
        return velocityParams;
    }

    public String getStringFromSingularObject(ApplicationUser singularObject) {
        return (singularObject != null) ? singularObject.getName() : null;
    }

    public ApplicationUser getSingularObjectFromString(String string) throws FieldValidationException {
        return ComponentAccessor.getUserManager().getUserByName(string);
    }
}
