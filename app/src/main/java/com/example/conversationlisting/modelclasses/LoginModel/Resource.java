
package com.example.conversationlisting.modelclasses.LoginModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Resource {

    @SerializedName("resource_id")
    @Expose
    private Integer resourceId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("contact_number")
    @Expose
    private String contactNumber;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("is_subscriber")
    @Expose
    private Boolean isSubscriber;
    @SerializedName("contact_extension")
    @Expose
    private String contactExtension;
    @SerializedName("mobile_number")
    @Expose
    private String mobileNumber;
    @SerializedName("resource_title")
    @Expose
    private String resourceTitle;
    @SerializedName("middle_name")
    @Expose
    private String middleName;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("is_systems_administrator")
    @Expose
    private Boolean isSystemsAdministrator;
    @SerializedName("is_accounts_administrator")
    @Expose
    private Boolean isAccountsAdministrator;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("import_resource_uid")
    @Expose
    private Object importResourceUid;
    @SerializedName("import_project_resuid")
    @Expose
    private Object importProjectResuid;
    @SerializedName("systemuser")
    @Expose
    private String systemuser;
    @SerializedName("resourcenotes")
    @Expose
    private Object resourcenotes;
    @SerializedName("is_external_user")
    @Expose
    private Boolean isExternalUser;
    @SerializedName("is_inactive")
    @Expose
    private Integer isInactive;
    @SerializedName("resource_image")
    @Expose
    private Object resourceImage;
    @SerializedName("resource_type")
    @Expose
    private String resourceType;
    @SerializedName("is_enabled")
    @Expose
    private Boolean isEnabled;
    @SerializedName("hourly_cost_rate")
    @Expose
    private Object hourlyCostRate;
    @SerializedName("hourly_charge_out_rate")
    @Expose
    private Object hourlyChargeOutRate;
    @SerializedName("financial_data")
    @Expose
    private Boolean financialData;
    @SerializedName("admin_access")
    @Expose
    private Boolean adminAccess;
    @SerializedName("resource_image_compress")
    @Expose
    private Object resourceImageCompress;

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getIsSubscriber() {
        return isSubscriber;
    }

    public void setIsSubscriber(Boolean isSubscriber) {
        this.isSubscriber = isSubscriber;
    }

    public String getContactExtension() {
        return contactExtension;
    }

    public void setContactExtension(String contactExtension) {
        this.contactExtension = contactExtension;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getResourceTitle() {
        return resourceTitle;
    }

    public void setResourceTitle(String resourceTitle) {
        this.resourceTitle = resourceTitle;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Boolean getIsSystemsAdministrator() {
        return isSystemsAdministrator;
    }

    public void setIsSystemsAdministrator(Boolean isSystemsAdministrator) {
        this.isSystemsAdministrator = isSystemsAdministrator;
    }

    public Boolean getIsAccountsAdministrator() {
        return isAccountsAdministrator;
    }

    public void setIsAccountsAdministrator(Boolean isAccountsAdministrator) {
        this.isAccountsAdministrator = isAccountsAdministrator;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Object getImportResourceUid() {
        return importResourceUid;
    }

    public void setImportResourceUid(Object importResourceUid) {
        this.importResourceUid = importResourceUid;
    }

    public Object getImportProjectResuid() {
        return importProjectResuid;
    }

    public void setImportProjectResuid(Object importProjectResuid) {
        this.importProjectResuid = importProjectResuid;
    }

    public String getSystemuser() {
        return systemuser;
    }

    public void setSystemuser(String systemuser) {
        this.systemuser = systemuser;
    }

    public Object getResourcenotes() {
        return resourcenotes;
    }

    public void setResourcenotes(Object resourcenotes) {
        this.resourcenotes = resourcenotes;
    }

    public Boolean getIsExternalUser() {
        return isExternalUser;
    }

    public void setIsExternalUser(Boolean isExternalUser) {
        this.isExternalUser = isExternalUser;
    }

    public Integer getIsInactive() {
        return isInactive;
    }

    public void setIsInactive(Integer isInactive) {
        this.isInactive = isInactive;
    }

    public Object getResourceImage() {
        return resourceImage;
    }

    public void setResourceImage(Object resourceImage) {
        this.resourceImage = resourceImage;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Object getHourlyCostRate() {
        return hourlyCostRate;
    }

    public void setHourlyCostRate(Object hourlyCostRate) {
        this.hourlyCostRate = hourlyCostRate;
    }

    public Object getHourlyChargeOutRate() {
        return hourlyChargeOutRate;
    }

    public void setHourlyChargeOutRate(Object hourlyChargeOutRate) {
        this.hourlyChargeOutRate = hourlyChargeOutRate;
    }

    public Boolean getFinancialData() {
        return financialData;
    }

    public void setFinancialData(Boolean financialData) {
        this.financialData = financialData;
    }

    public Boolean getAdminAccess() {
        return adminAccess;
    }

    public void setAdminAccess(Boolean adminAccess) {
        this.adminAccess = adminAccess;
    }

    public Object getResourceImageCompress() {
        return resourceImageCompress;
    }

    public void setResourceImageCompress(Object resourceImageCompress) {
        this.resourceImageCompress = resourceImageCompress;
    }

}
